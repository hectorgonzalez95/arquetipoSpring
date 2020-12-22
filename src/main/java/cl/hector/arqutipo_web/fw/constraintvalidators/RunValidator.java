package cl.hector.arqutipo_web.fw.constraintvalidators;

import cl.hector.arqutipo_web.fw.contraints.Run;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class RunValidator implements ConstraintValidator<Run, String> {

    @Override
    public void initialize(Run constraintAnnotation) {
        // Do nothing
    }

    @Override
    public boolean isValid(String run, ConstraintValidatorContext constraintValidatorContext) {
        try {
            run = run.toUpperCase().replace(".", "");
            String[] split = run.split("-");
            int rutAux = Integer.parseInt(split[0]);
            char dv = split[1].charAt(0);
            int m = 0;
            int s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            return (dv == (char) (s != 0 ? s + 47 : 75));
        } catch (NumberFormatException e) {
            log.error("FW3: Error al formatear run {}", run, e);
            return false;
        } catch (Exception e) {
            log.error("FW3: Error al validar run {}", run, e);
            return false;
        }
    }
}
