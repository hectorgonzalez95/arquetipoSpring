package cl.hector.arqutipo_web.fw.security.ldap.interfaces;

public interface DBUserService extends UserService {
    boolean loginUser(String userName, String password);
}
