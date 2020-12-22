package cl.hector.arqutipo_web.fw.security.ldap.interfaces;

public interface UserService {
    String[] getAuthorities(String userName);

    Object getUserDetails(String userName);
}

