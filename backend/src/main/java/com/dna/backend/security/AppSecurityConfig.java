package com.dna.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dna.backend.security.service.UserServiceImpl;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll();
//				.antMatchers("/**").hasRole(AppUserRole.ADMIN.name()) 
//				.anyRequest()
//				.authenticated();
//				.and()
//				.formLogin()   form base authentication
//				.loginPage("/login")
//				.permitAll()  to use my own login form
//				.defaultSuccessUrl("/courses", true)  redirect page after successfully login
//				.passwordParameter("password")
//				.usernameParameter("userName")
//				.and()
//				.rememberMe()  by default 2 weeks
//				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))  by now I am extending to 3 weeks
//				.rememberMeParameter("remember-me")
//				.key("secured")
//				.and()
//				.logout()
//				.logoutUrl("/logout")
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))  if csrf is enabled delete this line
//				.clearAuthentication(true)
//				.invalidateHttpSession(true)
//				.deleteCookies("JSESSIONID", "remember-me")
//				.logoutSuccessUrl("/login");

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());

		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}

}
