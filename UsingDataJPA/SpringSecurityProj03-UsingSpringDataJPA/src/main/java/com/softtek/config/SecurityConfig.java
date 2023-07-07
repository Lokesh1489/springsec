package com.softtek.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Authorize requests
		http.authorizeHttpRequests().antMatchers("/bank/").permitAll()// No Authentication and no Authorization
		.antMatchers("/user/register","/user/showLogin").permitAll()
				.antMatchers("/bank/offers").authenticated()// only authentication
				.antMatchers("/bank/balance").hasAnyAuthority("CUSTOMER", "MANAGER")// Authentication & Authorization for
																				// customer
																				// Manager role
				.antMatchers("/bank/loanApprove").hasAnyAuthority("MANAGER")// Authentication & Authorization for only
																		// Manager
				.anyRequest().authenticated() // remaining
				// .and().httpBasic()// specify authentication mode (just to get user name &
				// password)
				.and().formLogin().defaultSuccessUrl("/bank/",true).loginPage("/user/showLogin").loginProcessingUrl("/login").failureUrl("/user/showLogin?error")
				.and().rememberMe().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
				.logoutSuccessUrl("/user/showLogin?signout")
				// exception/error handling (for 403 error)
				.and().exceptionHandling().accessDeniedPage("/denied")

				// add sessionMaxCountcurrency count
				.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);

	}
}
