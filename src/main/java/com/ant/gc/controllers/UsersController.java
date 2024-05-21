package com.ant.gc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.gc.dto.MessageResponse;
import com.ant.gc.dto.PasswordDto;
import com.ant.gc.entities.Users;
import com.ant.gc.services.UsersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "hedha controller lel user")
@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {
	@Autowired
	private UsersService usersService;
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@ApiOperation(value = "jiib lkol")
	@GetMapping
	public List<Users> findAll() {
		return usersService.findAll();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "sob user")
	@PostMapping
	public MessageResponse save(@ApiParam(value = "objet user") @RequestBody Users user) {
		return usersService.save(user);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public MessageResponse update(@RequestBody Users user) {
		return usersService.update(user);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public Users findById(@PathVariable Integer id) {
		return usersService.findById(id);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public MessageResponse delete(@PathVariable Integer id) {
		return usersService.delete(id);
	}

	@PatchMapping
	public MessageResponse changePassword(@RequestBody PasswordDto passwordDto) {
		return usersService.changePassword(passwordDto);
	}

}
