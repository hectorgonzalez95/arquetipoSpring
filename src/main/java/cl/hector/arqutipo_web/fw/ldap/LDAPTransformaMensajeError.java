package cl.hector.arqutipo_web.fw.security.ldap;

import lombok.Builder;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Builder
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class LDAPTransformaMensajeError {

    String mensajeError;

    long transformar(){
        String codeStr;
        int index;

        if (mensajeError != null){
            index = mensajeError.indexOf("error, data ");
            if (index > -1 && index + "error, data ".length() + 3 < mensajeError.length()){
                index = index + "error, data ".length();
                codeStr = mensajeError.substring(index,index + 3);
                return LDAPException.parseError(codeStr);
            }
        }
        return LDAPException.GENERIC_ERROR;
    }
}
