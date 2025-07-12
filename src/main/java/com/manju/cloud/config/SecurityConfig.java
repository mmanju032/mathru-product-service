package com.manju.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("foo")
			.password("foo")
			.roles("USER")
			.and()
			.withUser("manju")
			.password("manju")
			.roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/product/addProduct/*").hasAnyRole("ADMIN")
				.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		System.out.println("PasswordEncoder bean created");
		
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	

}
