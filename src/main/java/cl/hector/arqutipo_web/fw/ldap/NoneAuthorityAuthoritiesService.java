package cl.hector.arqutipo_web.fw.security.ldap;

import cl.hector.arqutipo_web.fw.security.ldap.interfaces.DBUserService;

public class NoneAuthorityAuthoritiesService implements DBUserService {

    @Override
    public String[] getAuthorities(String userName) {
        return new String[0];
    }

    @Override
    public Object getUserDetails(String userName) {
        return userName;
    }

    @Override
    public boolean loginUser(String userName, String password) {
        return true;
    }

}
