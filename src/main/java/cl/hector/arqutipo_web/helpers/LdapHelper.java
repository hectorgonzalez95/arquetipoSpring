package cl.hector.arqutipo_web.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Component
@Slf4j
public class LdapHelper {

	@Autowired private Environment env;
	
	public Boolean validaUsuarioLDAP(String strUsuario, String strPassword) {
		Hashtable<String, String> environment = new Hashtable<String, String>();
		boolean conectado;
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, "LDAP://" + env.getProperty("cl.mineduc.ldap.domain.controller"));
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, env.getProperty("cl.mineduc.ldap.domain") + "\\" + strUsuario);
		environment.put(Context.SECURITY_CREDENTIALS, strPassword);
		try {
			DirContext context = new InitialDirContext(environment);
			conectado = true;
			context.close();
		} catch (AuthenticationException e) {
			log.info("Ha ocurrido una excepcion al autorizar al usuario en ldap [{}] ",strUsuario, e);
			conectado = false;
		} catch (NamingException e) {
			log.error("Ha ocurrido una excepcion al Iniciar el contexto LDAP ", e);
			conectado = false;
		}
		return conectado;
	}
}
