package com.ant.gc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class GestionContact1Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(GestionContact1Application.class, args);

//		  SpringApplication.exit(context, new ExitCodeGenerator() {
//			
//			@Override
//			public int getExitCode() {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		});

	}

	

}
