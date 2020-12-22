package cl.hector.arqutipo_web.fw.security.ldap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class SimpleAuthentication implements Authentication {

    private final String principal;
    private final List<GrantedAuthority> authorities;
    private final Object detail;
    private boolean authenticated;

    public SimpleAuthentication(String p, List<GrantedAuthority> auths, Object d) {
        this.principal = p;
        this.authorities = auths;
        this.detail = d;
        this.authenticated = true;
    }

    @Override
    public String getName() {
        return principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return detail;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        authenticated = isAuthenticated;
    }

}

