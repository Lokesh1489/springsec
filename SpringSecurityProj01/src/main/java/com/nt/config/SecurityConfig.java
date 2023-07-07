package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Sanjay").password("{noop}sanj").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("Lokesh").password("{noop}loke").roles("MANAGER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/offers").authenticated()
				.antMatchers("/balance").hasAnyRole("CUSTOMER", "MANAGER").antMatchers("/loanApprove")
				.hasRole("MANAGER").anyRequest().authenticated()
				.and().formLogin().and().rememberMe().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
				.and().exceptionHandling().accessDeniedPage("/denied")
				.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);

	}

}
