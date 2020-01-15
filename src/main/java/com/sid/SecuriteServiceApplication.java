package com.sid;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sid.entities.AppRole;
import com.sid.service.AccountService;

@SpringBootApplication
public class SecuriteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuriteServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args->{
			accountService.saveRole(new AppRole(null,"USER"));
			accountService.saveRole(new AppRole(null,"ADMIN"));
			Stream.of("user1","user2","user3","admin").forEach(un->{
				accountService.saveUser(un, "123", "123");
			});
			
			accountService.AddRoleToUser("admin", "ADMIN");
		};
	}
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
