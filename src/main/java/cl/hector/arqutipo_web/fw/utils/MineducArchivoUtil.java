package cl.hector.arqutipo_web.fw.utils;

import cl.hector.arqutipo_web.fw.errors.MineducException;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Builder
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MineducArchivoUtil {

    String ruta;
    String nombreArchivo;
    MultipartFile archivo;

    public String subirArchivo(){
        try{
            String nuevoNombreArchivo = StringUtils.isEmpty(this.nombreArchivo)?LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss")):this.nombreArchivo;
            String[] split =  Objects.requireNonNull(archivo.getOriginalFilename()).split("\\.");
            String extension = split[split.length-1];
            nuevoNombreArchivo = nuevoNombreArchivo.concat(".").concat(extension);
            log.debug("FW3: El nombre del archivo guardado sera {}", nuevoNombreArchivo);
            Files.createDirectories(Paths.get(ruta));
            log.debug("FW3: Se crea la ruta {} para almacenar el archivo {}", ruta, nuevoNombreArchivo);
            String uploadDir = ruta.concat(nuevoNombreArchivo);
            Path fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.copy(archivo.getInputStream(), fileStorageLocation, StandardCopyOption.REPLACE_EXISTING);
            log.debug("FW3: Archivo subido correctamente");
            return nuevoNombreArchivo;
        }catch (IOException e) {
            log.error("FW3: Error al subir el archivo",e);
            throw new MineducException("Error al subir el archivo",e);
        }
    }
}
