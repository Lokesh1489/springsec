package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityProj02UsingJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProj02UsingJdbcApplication.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd1 = encoder.encode("softtek@2021");
		String pwd2 = encoder.encode("softtek@2023");
		System.out.println(pwd1 + "........." + pwd2);
	}

}
