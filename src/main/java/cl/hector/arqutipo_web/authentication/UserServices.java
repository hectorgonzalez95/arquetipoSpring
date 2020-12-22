package cl.hector.arqutipo_web.authentication;

import cl.mineduc.framework2.security.spring.UserService;
import cl.hector.arqutipo_web.models.auth.Usuario;

public class UserServices implements UserService {

	@Override
	public String[] getAuthorities(String userName) {
		return new String[]{"ROLE_USER"};
	}

	@Override
	public Object getUserDetails(String userName) {
		// Aqui debemos crear o buscar nuestro propio objeto de usuario, este ejemplo solo guarda el nombre 
		// Se debe crear un a clase Usuario y devlover esta en este metodo
		return new Usuario();
	}	
}
