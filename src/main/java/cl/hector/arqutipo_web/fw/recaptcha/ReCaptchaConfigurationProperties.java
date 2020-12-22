package cl.hector.arqutipo_web.fw.recaptcha;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class ReCaptchaConfigurationProperties {

    String secreto = "6LcZLv8UAAAAAKxRb65Kp9XzaYcnMkc_BI4nUuli";
    String[] urlsProtegidas;
    String nombreVariableReCaptchaResponse = "g-recaptcha-response";
    float limite = 0.5f;

}

