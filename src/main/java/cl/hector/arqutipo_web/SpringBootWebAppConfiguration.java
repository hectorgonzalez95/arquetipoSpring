package cl.hector.arqutipo_web;

import cl.mineduc.framework2.web.interceptors.UserAgentInterceptor;
import cl.hector.arqutipo_web.interceptors.SpringBootWebAppAddCommonVariablesInterceptor;
import cl.hector.arqutipo_web.interceptors.MDCInterceptor;
import cl.hector.arqutipo_web.services.SpringBootWebAppCommonModelService;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Properties;


/**
 * @author Rodrigo Alvarez, Alvaro Tellez
 *
 */
@Configuration
@EnableAutoConfiguration(exclude={XADataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {
		"cl.hector.arqutipo_web.controllers",
		"cl.hector.arqutipo_web.controllers_advice",
		"cl.hector.arqutipo_web.services",
		"cl.hector.arqutipo_web.helpers",
		"cl.hector.arqutipo_web.repositories"})
@MapperScan
public class SpringBootWebAppConfiguration implements WebMvcConfigurer{
	
	@Autowired 
	private Environment env;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		WebMvcConfigurer.super.addViewControllers(registry);
	}
	
	@Bean
	public HandlerExceptionResolver annotationMethodHandlerExceptionResolver() {
		ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setDefaultEncoding("UTF-8");
		Properties fileEncodings = new Properties();
		fileEncodings.setProperty("application-arqutipo-web-messages", "UTF-8");
		bundle.setFileEncodings(fileEncodings);
		bundle.setFallbackToSystemLocale(true);
		bundle.setBasename("classpath:application-arqutipo-web-messages");
		return bundle;
	}
	
	@Bean(name="commonService")
	public SpringBootWebAppCommonModelService commonModelService(){
		SpringBootWebAppCommonModelService service = new SpringBootWebAppCommonModelService();
		service.setDevelopIPs(env.getProperty("cl.mineduc.debug.ips", "127.0.0.1,0:0:0:0:0:0:0:1"));
		return service;
	}	
	
	@Bean
	public HandlerInterceptor addCommonVariablesInterceptor(){
		SpringBootWebAppAddCommonVariablesInterceptor interceptor = new SpringBootWebAppAddCommonVariablesInterceptor();
		interceptor.setCommonService(commonModelService());
		return interceptor;
	}
	
	@Bean
	public HandlerInterceptor addMDCInterceptor() {
		return new MDCInterceptor();
	}
	
	@Bean
	public HandlerInterceptor userAgentInterceptor(){
		return new UserAgentInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.addMDCInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(this.addCommonVariablesInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(this.userAgentInterceptor()).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Bean
	public Credentials credentials() {
		return new UsernamePasswordCredentials("", ""); // en caso de usar basic http auth
	}
	
	@Bean
	public CredentialsProvider credentialsProvider() {
		BasicCredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, credentials());
		return provider;
	}
	
	@Bean
	public HttpComponentsClientHttpRequestFactory httpFactory() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider());
		CloseableHttpClient httpClient = httpClientBuilder.build();
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(httpFactory());
	}
}
