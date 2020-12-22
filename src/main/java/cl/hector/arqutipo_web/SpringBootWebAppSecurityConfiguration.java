package cl.hector.arqutipo_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import cl.mineduc.framework2.security.spring.DBAuthenticationProvider;
import cl.mineduc.framework2.security.spring.LdapAuthenticationProvider;
import cl.mineduc.framework2.security.spring.UserService;
import cl.hector.arqutipo_web.authentication.DBUserServices;
import cl.hector.arqutipo_web.authentication.UserServices;


/**
 * @author Rodrigo Alvarez Chanchito My Friend, Alvaro Tellez
 *
 */
@Configuration
@ComponentScan(basePackages = {"cl.hector.arqutipo_web.authentication"})
@EnableWebSecurity
public class SpringBootWebAppSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public UserService userService() {
		return new UserServices();
	}
	
	@Bean
	public DBUserServices dbUserServices(){
		return new DBUserServices();
	}

	@Bean
	public LdapAuthenticationProvider ldapProvider() {
		LdapAuthenticationProvider provider = new LdapAuthenticationProvider();
		provider.setUserService(userService());

		return provider;
	}
	
	@Bean
	public DBAuthenticationProvider backendAuthenticationProvider(){
		DBAuthenticationProvider provider = new DBAuthenticationProvider();
		provider.setUserService(dbUserServices());
		return provider;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/locals/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
				.antMatchers("/login/**").permitAll()
				.anyRequest().authenticated()
			//.and().exceptionHandling().accessDeniedPage("/mvc/errors/accessDenied")
		.and()
			.formLogin()
				.loginPage("/login/login")
				.loginProcessingUrl("/login/do_login")
				.defaultSuccessUrl("/home/index", true)
				.failureUrl("/login/login?error=1")
				.usernameParameter("txtUsuario")
				.passwordParameter("txtClave")
				.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("1").password("1").roles("USER");
		//auth.authenticationProvider(ldapProvider())
		auth.authenticationProvider(backendAuthenticationProvider());
	}
}