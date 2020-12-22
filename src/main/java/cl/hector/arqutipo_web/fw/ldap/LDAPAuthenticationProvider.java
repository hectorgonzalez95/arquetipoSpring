package cl.hector.arqutipo_web.fw.security.ldap;

import cl.hector.arqutipo_web.fw.errors.MineducException;
import cl.hector.arqutipo_web.fw.security.ldap.interfaces.UserService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LDAPAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    LDAPHelper ldapServices;
    UserService userService = new NoneAuthorityAuthoritiesService();

    @Override
    public Authentication authenticate(Authentication authentication) {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        List<GrantedAuthority> authorities = null;
        Object detail;
        String userName, password;
        String[] roles;
        log.debug("FW3: Autenticación contra LDPA iniciada para el usuario {}", auth.getPrincipal());
        try {
            userName = String.valueOf(auth.getPrincipal());
            password = String.valueOf(auth.getCredentials());
            ldapServices.validaUsuarioLDAP(userName, password);
            roles = userService.getAuthorities(userName);
            if ((roles != null) && (roles.length > 0)) {
                authorities = new ArrayList<>(roles.length);
                for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }
            }
            detail = userService.getUserDetails(userName);
        } catch (LDAPException e) {
            log.error("FW3: Error al autenticar contra LDAP", e);
            throw new BadCredentialsException("Error al autenticar contra Directorio LDAP", e);
        } catch (MineducException e) {
            throw new AuthenticationServiceException("Error al autenticar", e);
        }
        return new SimpleAuthentication(userName, authorities, detail);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
