package cl.hector.arqutipo_web.fw.recaptcha;

import lombok.Builder;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Builder
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ReCaptchaValidator {

    static RestTemplate restTemplate = new RestTemplate();
    static String verifyUri = "https://www.google.com/recaptcha/api/siteverify?secret={secret}&response={response}&remoteip={ip}";

    String secreto;
    String respuestaReCaptchaUsuario;
    String ip;
    float limite;

    boolean validate() {
        GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class, secreto, respuestaReCaptchaUsuario, ip);
        assert googleResponse != null;
        log.debug("FW3: La respuesta desde Google reCaptcha es {}", googleResponse.toString());
        return googleResponse.isSuccess() && googleResponse.getAction().equals("validate_captcha") && googleResponse.getScore() >= limite;
    }
}
