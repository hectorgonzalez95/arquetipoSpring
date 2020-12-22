package cl.hector.arqutipo_web.fw.encryptor;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE)
public class MineducEncriptadorHelper implements InitializingBean {

    StandardPBEStringEncryptor standardPBEStringEncryptor;
    StandardStringDigester standardStringDigester;
    final String algorithm;
    final String textOutput;
    final String password;
    final int iterations;

    public MineducEncriptadorHelper(EncriptadorConfigurationProperties encriptadorConfigurationProperties){
        this.algorithm = encriptadorConfigurationProperties.getAlgorithm();
        this.textOutput = encriptadorConfigurationProperties.getTextOutput();
        this.password = encriptadorConfigurationProperties.getPassword();
        this.iterations = encriptadorConfigurationProperties.getIterations();
    }

    @PostConstruct
    private void encriptador() {
        this.standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        this.standardPBEStringEncryptor.setPassword(this.password);
        this.standardPBEStringEncryptor.setStringOutputType(this.textOutput);
        log.debug("FW3: Encriptador de texto configurado correctamente");
        this.standardStringDigester = new StandardStringDigester();
        this.standardStringDigester.setAlgorithm(this.algorithm);
        this.standardStringDigester.setIterations(this.iterations);
        log.debug("FW3: Encriptador de password configurado correctamente");
    }

    @Override
    public void afterPropertiesSet(){
        log.debug("MineducEncriptadorHelper configurado correctamente");
    }

    public String encryptMessage(String message){
        return this.standardPBEStringEncryptor.encrypt(message);
    }

    public String decryptMessage(String message){
        return this.standardPBEStringEncryptor.decrypt(message);
    }

    public String encryptPassword(String plainPassword){
        return this.standardStringDigester.digest(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String digestedPassword){
        return standardStringDigester.matches(plainPassword, digestedPassword);
    }
}
