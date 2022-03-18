package fr.univlille.redspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.httpBasic().and()
		/*.requiresChannel()
		  .anyRequest().requiresSecure()
		  .and()*/
		.authorizeRequests()
			.antMatchers("/h2-console/**").hasRole("DEVELOPERS")
			.antMatchers("/api/**").authenticated()
			.and()
		.csrf()
            .disable()
        /*.headers()
            .frameOptions().sameOrigin()*/;
	} 	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.ldapAuthentication()
	        .userDnPatterns("uid={0},ou=people")
	        .userSearchBase("ou=people")
	        .userSearchFilter("uid={0}")
	        .groupSearchBase("ou=groups")
	        .groupSearchFilter("uniqueMember={0}")
	        .contextSource()
	        .url("ldap://localhost:2389/dc=redteam,dc=fr")
	        .and()
	        .passwordCompare()
	        .passwordEncoder(passwordEncoder())
	        .passwordAttribute("userPassword");		
	}
	
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(false);
	    return loggingFilter;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

} 