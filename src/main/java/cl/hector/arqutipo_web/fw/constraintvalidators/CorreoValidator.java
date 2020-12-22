package cl.hector.arqutipo_web.fw.constraintvalidators;

import cl.hector.arqutipo_web.fw..contraints.Correo;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorreoValidator implements ConstraintValidator<Correo, String> {

    protected boolean requerido;

    @Override
    public void initialize(Correo constraintAnnotation) {
        this.requerido = constraintAnnotation.requerido();
    }

    @Override
    public boolean isValid(String correo, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isEmpty(correo) && !requerido){
            return true;
        }
        if(!isValid(correo)){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("El correo electrónico no cumple con el formato correcto")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private boolean isValid(String correo) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(correo);
        return (matcher.find());
    }

}