package cl.hector.arqutipo_web.fw.contraints;

import cl.hector.arqutipo_web.fw.constraintvalidators.ArchivoValidator;
import cl.hector.arqutipo_web.fw.utils.MineducMimeTypesUtil;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Archivo.List.class)
@Documented
@Constraint(validatedBy = {ArchivoValidator.class})
public @interface Archivo {

    String message() default "Error al validar Archivo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean requerido() default false;
    MineducMimeTypesUtil[] mimeTypes() default {MineducMimeTypesUtil.MIME_APPLICATION_OCTET_STREAM};
    int maxMb() default 1;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Archivo[] value();
    }

}
