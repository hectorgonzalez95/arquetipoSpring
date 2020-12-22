package cl.hector.arqutipo_web.fw.security.ldap;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE)
public class ActiveDirectory {
    DirContext dirContext;
    final SearchControls searchCtls;
    final String domainBase;

    /**
     * constructor with parameter for initializing a LDAP context
     *
     * @param username         a {@link String} object - username to establish a LDAP connection
     * @param password         a {@link String} object - password to establish a LDAP connection
     * @param domainController a {@link String} object - domain controller name for LDAP connection
     */
    public ActiveDirectory(String username, String password, String domainController, String domain) {
        Properties properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
        properties.put(Context.SECURITY_PRINCIPAL, domain + "\\" + username);
        properties.put(Context.SECURITY_CREDENTIALS, password);
        properties.put(Context.REFERRAL, "follow");

        try {
            dirContext = new InitialDirContext(properties);
        } catch (NamingException e) {
            log.error("FW3: Error al conectar a LDAP", e);
            long errorCode = LDAPTransformaMensajeError.builder().mensajeError(e.getMessage()).build().transformar();
            throw new LDAPException(errorCode,"Problemas al conectarse con Directorio LDAP",e);
        }
        domainBase = getDomainBase(domainController);
        searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String[] returnAttributes = {"sAMAccountName", "givenName", "cn", "mail", "sn", "telephonenumber", "employeeid", "description"};
        searchCtls.setReturningAttributes(returnAttributes);
    }

    /**
     * creating a domain base value from domain controller name
     *
     * @param base a {@link String} object - name of the domain controller
     * @return a {@link String} object - base name for eg. DC=myjeeva,DC=com
     */
    private static String getDomainBase(String base) {
        char[] namePair = base.toUpperCase().toCharArray();
        StringBuilder dn = new StringBuilder("DC=");
        for (int i = 0; i < namePair.length; i++) {
            if (namePair[i] == '.') {
                dn.append(",DC=").append(namePair[++i]);
            } else {
                dn.append(namePair[i]);
            }
        }
        return dn.toString();
    }

    /**
     * search the Active directory by username/email id for given search base
     *
     * @param searchValue a {@link String} object - search value used for AD search for eg. username or email
     * @param searchBy    a {@link String} object - scope of search by username or by email id
     * @param searchBase  a {@link String} object - search base value for scope tree for eg. DC=myjeeva,DC=com
     * @return search result a {@link NamingEnumeration} object - active directory search result
     */
    public NamingEnumeration<SearchResult> searchUser(String searchValue, String searchBy, String searchBase) throws NamingException {
        String filter = getFilter(searchValue, searchBy);
        String base = (null == searchBase) ? domainBase : getDomainBase(searchBase);
        return this.dirContext.search(base, filter, this.searchCtls);
    }

    /**
     * closes the LDAP connection with Domain controller
     */
    public void closeLdapConnection() {
        try {
            if (dirContext != null)
                dirContext.close();
        } catch (NamingException e) {
            log.error("FW3: Error al cerrar la conexion al LDAP", e);
            long errorCode = LDAPTransformaMensajeError.builder().mensajeError(e.getMessage()).build().transformar();
            throw new LDAPException(errorCode,"Problemas al cerrar conexion con Directorio LDAP",e);
        }
    }

    /**
     * active directory filter string value
     *
     * @param searchValue a {@link String} object - search value of username/email id for active directory
     * @param searchBy    a {@link String} object - scope of search by username or email id
     * @return a {@link String} object - filter string
     */
    private String getFilter(String searchValue, String searchBy) {
        String baseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";
        String filter = baseFilter;
        if (searchBy.equals("email")) {
            filter += "(mail=" + searchValue + "))";
        } else if (searchBy.equals("username")) {
            filter += "(samaccountname=" + searchValue + "))";
        }
        return filter;
    }
}
