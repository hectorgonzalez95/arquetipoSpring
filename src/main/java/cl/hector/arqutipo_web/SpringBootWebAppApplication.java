package cl.hector.arqutipo_web;

import cl.hector.arqutipo_web.interceptors.MDCInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootWebAppApplication {

	public static void main(String[] args) {
		MDCInterceptor.appIsBooting();
		SpringApplication.run(SpringBootWebAppApplication.class, args);
		log.info(SpringBootWebAppApplication.class.getCanonicalName()+ " HAS DEBUG ENABLED : {}",log.isDebugEnabled());
		log.info(SpringBootWebAppApplication.class.getName()+" UP & RUNNING!!!");
	}
}
