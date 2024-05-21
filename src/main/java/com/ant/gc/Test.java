package com.ant.gc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ant.gc.entities.Users;
import com.ant.gc.services.UsersService;

@Component
public class Test implements CommandLineRunner {

	@Autowired
	private UsersService usersService;

	@Override
	public void run(String... args) throws Exception {

//		Users user = new Users();
//		user.setNom("Foulen");
//		user.setPrenom("Eleen");
//		user.setEmail("foulen@gmail.com");
//		user.setUsername("admin");
//		user.setPassword("admin");
//		user.setRole("ROLE_ADMIN");
//		user.setAdresse("Tunis");
//		usersService.save(user);
//		List<Users> list = usersService.findAll();
//		System.out.println(list.size());
//		List list1 = usersService.findAll();
//		System.out.println(list1.size());
	}

}
