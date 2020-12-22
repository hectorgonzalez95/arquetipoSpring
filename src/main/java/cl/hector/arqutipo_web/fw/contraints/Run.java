package cl.hector.arqutipo_web.fw.contraints;

import cl.hector.arqutipo_web.fw.constraintvalidators.RunValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Run.List.class)
@Documented
@Constraint(validatedBy = {RunValidator.class})
public @interface Run {

    String message() default "El RUN no es válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Run[] value();
    }

}