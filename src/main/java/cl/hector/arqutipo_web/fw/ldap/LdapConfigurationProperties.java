package cl.hector.arqutipo_web.fw.security.ldap;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE)
public class LdapConfigurationProperties {

    String userName = "certificado_digital";
    String password = "Qkq9i4XD";
    String domainController = "minduccentral.mineduc.cl:389";
    String searchBase = "mineduc.cl";
    String domain = "mineduca";

}
