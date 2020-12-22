package cl.hector.arqutipo_web.authentication;

import cl.mineduc.framework2.security.spring.DBUserService;
import cl.hector.arqutipo_web.models.auth.Estado;
import cl.hector.arqutipo_web.models.auth.Usuario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBUserServices implements DBUserService {

	private Usuario usuario;

	@Override
	public String[] getAuthorities(String userName) {
		/**TODO:fetch usuario from database or service, then get authorities**/
		String[] roles = {"ADMIN"};
		return roles;
	}

	@Override
	public Object getUserDetails(String userName) {
		/**TODO:fetch usuario from database or service**/
		return this.usuario;
	}

	@Override
	public boolean loginUser(String userName, String password) {
		/**TODO: implementar funcion de login contra base de datos o api**/
		boolean mockLoginOk = true;
		
		/**TODO: instanciar modelo usuario con atributos y privilegios**/
		this.usuario = new Usuario();
		usuario.setLogin(userName);
		usuario.setNombre("USUARIO MOCK");
		usuario.setPaterno("IMPLEMENTAME");
		usuario.setEstado(Estado.ACTIVO);
		
		
		if(mockLoginOk ) {
			log.info("USUARIO INSTANCIADO");
			return true;
		}
		return false;
	}

}
