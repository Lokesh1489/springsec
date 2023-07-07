package com.nt.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERS WHERE UNAME=?") // for authenciation
				.authoritiesByUsernameQuery("SELECT UNAME,ROLE FROM USER-ROLES WHERE UNAME=?"); // for authorization
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/offers").authenticated()
				.antMatchers("/balance").hasAnyAuthority("CUSTOMER", "MANAGER").antMatchers("/loanApprove")
				.hasAuthority("MANAGER").anyRequest().authenticated().and().formLogin().and().rememberMe().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/signout")).and().exceptionHandling()
				.accessDeniedPage("/denied").and().sessionManagement().maximumSessions(2)
				.maxSessionsPreventsLogin(true);
	}
}
