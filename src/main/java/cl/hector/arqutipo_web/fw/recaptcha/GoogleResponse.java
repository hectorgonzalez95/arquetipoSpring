package cl.hector.arqutipo_web.fw.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@JsonPropertyOrder({
        "success",
        "score",
        "action",
        "challenge_ts",
        "hostname",
        "error-codes"
})
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@ToString
public class GoogleResponse {

    boolean success;
    float score;
    String action;
    @JsonProperty("challenge_ts")
    private LocalDateTime challengeTs;
    private String hostname;
    @JsonProperty("error-codes")
    private String[] errorCodes;

}
