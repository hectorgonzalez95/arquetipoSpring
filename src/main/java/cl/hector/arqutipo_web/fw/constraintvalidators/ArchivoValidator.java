package cl.hector.arqutipo_web.fw.constraintvalidators;

import cl.hector.arqutipo_web.fw..contraints.Archivo;
import cl.hector.arqutipo_web.fw.utils.MineducMimeTypesUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ArchivoValidator implements ConstraintValidator<Archivo, MultipartFile> {

    protected boolean requerido;
    protected MineducMimeTypesUtil[] mineducMimeTypeUtils;
    protected int maxMb;


    @Override
    public void initialize(Archivo constraintAnnotation) {
        this.requerido = constraintAnnotation.requerido();
        this.mineducMimeTypeUtils = constraintAnnotation.mimeTypes();
        this.maxMb = constraintAnnotation.maxMb();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        double tamanio = obtenerTamanioEnMegabytes(multipartFile.getSize());

        if(requerido && multipartFile.getSize() == 0){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Es obligatorio ingresar un archivo")
                    .addConstraintViolation();
            return false;
        }
        if(tamanio==0) return true;

        boolean noExistenErrores = true;
        if(tamanio > maxMb){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("El peso máximo del archivo es "+maxMb+"MB, peso actual "+tamanio+"MB")
                    .addConstraintViolation();
            noExistenErrores = false;
        }

        boolean existe = Arrays.stream(mineducMimeTypeUtils).anyMatch(a -> multipartFile.getContentType().contains(a.getMimeType()));
        if(!existe){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Solo se permite subir archivos de tipo: "+ Arrays.toString(mineducMimeTypeUtils))
                    .addConstraintViolation();
            noExistenErrores = false;
        }
        return noExistenErrores;
    }

    private double obtenerTamanioEnMegabytes(long size){
        return size * 0.00000095367432;
    }
}
