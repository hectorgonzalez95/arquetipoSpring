package cl.hector.arqutipo_web.fw.security.ldap.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class LDAPUser implements Serializable {

    private static final long serialVersionUID = 6248441950753499422L;
    private int run;
    private String digitoVerificador;
    private String nombres;
    private String paterno;
    private String materno;
    private String email;
    private String loginUser;

}
