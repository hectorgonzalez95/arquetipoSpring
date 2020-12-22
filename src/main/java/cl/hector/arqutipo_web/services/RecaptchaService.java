package cl.hector.arqutipo_web.services;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecaptchaService {
	private static Logger logger = LogManager.getLogger(RecaptchaService.class);
	
	@Autowired
	private ObjectMapper jsonMapper;
	
	@Value(value = "${cl.hector.arqutipo_web.recaptcha.private.key}")
	private String recaptchaPrivateKey;
	
	public static final String RECHAPTCHA_VERIFY_URL="https://www.google.com/recaptcha/api/siteverify";
	
	public boolean recaptchaValidation(String recaptchaResponse, String clientRemoteAddres){
		try {
			URL url = new URL(RECHAPTCHA_VERIFY_URL+"?secret="+recaptchaPrivateKey+"&response="+recaptchaResponse+"&remoteip="+clientRemoteAddres);  
			JsonNode response = jsonMapper.readTree(url);
			Boolean success = response.findValue("success").asBoolean();
			if(!(null == response) && success) {
				Double score = response.findValue("score").asDouble();
				if(score>=0.5) {
					return true;
				}else {
					return false;
				}
			}
			return false;
		} catch (ProtocolException e) {
			logger.error("ERROR de Protocolo http al llamar al servicio reCaptcha de Google",e);
			return false;
		} catch (IOException e) {
			logger.error("ERROR de I/O http al llamar al servicio reCaptcha de Google",e);
			return false;
		}
	}
}
