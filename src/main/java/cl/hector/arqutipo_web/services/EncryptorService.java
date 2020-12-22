package cl.hector.arqutipo_web.services;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class EncryptorService implements InitializingBean{

	private StandardPBEStringEncryptor textEncryptor;
	private StandardStringDigester digester;
	
	@Value("${cl.hector.arqutipo_web.encryptor.algorithm}")
	private String algorithm;
	@Value("${cl.hector.arqutipo_web.encryptor.text.encrypt.output.type}")
	private String textOutput;
	@Value("${cl.hector.arqutipo_web.encryptor.text.encrypt.password}")
	private String password;
	@Value("${cl.hector.arqutipo_web.encryptor.iterations}")
	private Integer iterations;
	
	@PostConstruct
	private void encryptor() {
		/** sets the text encrypt **/
		this.textEncryptor = new StandardPBEStringEncryptor();
		this.textEncryptor.setPassword(this.password);
		this.textEncryptor.setStringOutputType(this.textOutput);
		log.debug("ENCRYPTOR SERVICE TEXT SETTINGS OK");
		/** sets the digester **/
		this.digester = new StandardStringDigester();
		this.digester.setAlgorithm(this.algorithm);
		this.digester.setIterations(this.iterations);
		log.debug("ENCRYPTOR SERVICE DIGESTER SETTINGS OK");
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("BEAN ENCRYPTOR INICIALIZADO!");
	}
	
	public String encryptMessage(String message){
		return this.textEncryptor.encrypt(message);
	}
	
	public String decryptMessage(String message){
		return this.textEncryptor.decrypt(message);
	}	
	
	public String encryptPassword(String plainPassword){
		return this.digester.digest(plainPassword);
	}
	
	public boolean checkPassword(String plainPassword, String digestedPassword){
		return digester.matches(plainPassword, digestedPassword);
	}

}
