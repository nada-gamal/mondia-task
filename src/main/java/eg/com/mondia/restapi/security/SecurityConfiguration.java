package eg.com.mondia.restapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static String REALM="MONDIA_REST_REALM";
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = passwordEncoder();
		auth.inMemoryAuthentication().withUser("mondia").password(encoder.encode("mondia")).roles("ADMIN");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
	  http.csrf().disable()
	  	.authorizeRequests()
	  	.antMatchers("/product/**").hasRole("ADMIN")
		.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
 	}
	
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
