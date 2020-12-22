package cl.hector.arqutipo_web.fw.security.ldap;

import cl.hector.arqutipo_web.fw.errors.MineducException;
import cl.hector.arqutipo_web.fw.security.ldap.models.LDAPUser;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class LDAPHelper {

    String userName;
    String password;
    String domainController;
    String searchBase;
    String domain;


    public LDAPHelper(LdapConfigurationProperties ldapConfigurationProperties) {
        this.userName = ldapConfigurationProperties.getUserName();
        this.password = ldapConfigurationProperties.getPassword();
        this.domainController = ldapConfigurationProperties.getDomainController();
        this.searchBase = ldapConfigurationProperties.getSearchBase();
        this.domain = ldapConfigurationProperties.getDomain();
    }

    public void validaUsuarioLDAP(String strUsuario, String strPassword) {
        Hashtable<String, String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, "LDAP://".concat(this.domainController));
        environment.put(Context.SECURITY_PRINCIPAL, this.domain.concat("\\").concat(strUsuario));
        environment.put(Context.SECURITY_CREDENTIALS, strPassword);
        try {
            DirContext context = new InitialDirContext(environment);
            context.close();
        } catch (NamingException e) {
            log.error("FW3: Ha ocurrido una excepcion al buscar usuario en ldap {}", strUsuario, e);
            long errorCode = LDAPTransformaMensajeError.builder().mensajeError(e.getMessage()).build().transformar();
            throw new LDAPException(errorCode,"Problemas al conectarse con Directorio LDAP",e);
        }
    }


    public LDAPUser obtenerUsuarioLdap(String userName) {
        ActiveDirectory ac = new ActiveDirectory(this.userName, this.password, this.domainController, this.domain);
        LDAPUser ldapUser = null;
        try {
            NamingEnumeration<SearchResult> result = ac.searchUser(userName, "username", this.searchBase);
            if (result.hasMore()) {
                ldapUser = new LDAPUser();
                String strTemp;
                SearchResult rs = result.next();
                Attributes attrs = rs.getAttributes();

                strTemp = attrs.get("samaccountname").toString();
                ldapUser.setLoginUser(strTemp.substring(strTemp.indexOf(":") + 1).trim());

                strTemp = attrs.get("givenname").toString();
                ldapUser.setNombres(strTemp.substring(strTemp.indexOf(":") + 1).trim());

                if (attrs.get("sn") != null) {
                    strTemp = attrs.get("sn").toString();
                    if (limpiaCadena(strTemp).length > 1) {
                        ldapUser.setPaterno(limpiaCadena(strTemp)[0]);
                        ldapUser.setMaterno(limpiaCadena(strTemp)[1]);
                    } else {
                        if (limpiaCadena(strTemp).length == 1) {
                            ldapUser.setPaterno(limpiaCadena(strTemp)[0]);
                        }
                    }
                }
                if (attrs.get("mail") != null) {
                    strTemp = attrs.get("mail").toString();
                    ldapUser.setEmail(strTemp.substring(strTemp.indexOf(":") + 1).trim());
                }
                if (attrs.get("employeeid") != null) {
                    strTemp = attrs.get("employeeid").toString();
                    String rutCompleto = strTemp.substring(strTemp.indexOf(":") + 1).trim();
                    ldapUser.setRun(Integer.parseInt((rutCompleto.split("-")[0])));
                    ldapUser.setDigitoVerificador((rutCompleto.split("-")[1]));
                }
            } else {
                throw new MineducException("Usuario no encontrado en Ldap");
            }
        } catch (NamingException e) {
            log.error("FW3: Error conectando a LDAP", e);
            long errorCode = LDAPTransformaMensajeError.builder().mensajeError(e.getMessage()).build().transformar();
            throw new LDAPException(errorCode,"Problemas al conectarse con Directorio LDAP",e);
        } finally {
            ac.closeLdapConnection();
        }
        return ldapUser;
    }

    private String[] limpiaCadena(String cadena){
        return cadena.substring(cadena.indexOf(":") + 1).trim().split(" ");
    }
}

