package cl.hector.arqutipo_web.fw.contraints;

import cl.hector.arqutipo_web.fw.constraintvalidators.CorreoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Correo.List.class)
@Documented
@Constraint(validatedBy = {CorreoValidator.class})
public @interface Correo {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean requerido() default false;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Correo[] value();
    }

}