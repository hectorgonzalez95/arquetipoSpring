package cl.hector.arqutipo_web.fw.encryptor;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class EncriptadorConfigurationProperties {

    String algorithm = "SHA-512";
    String textOutput = "hexadecimal";
    String password = "$yakarta2020#.";
    int iterations = 50000;

}
