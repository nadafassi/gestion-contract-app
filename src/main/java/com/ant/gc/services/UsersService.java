package com.ant.gc.services;

import java.util.List;

import com.ant.gc.dto.MessageResponse;
import com.ant.gc.dto.PasswordDto;
import com.ant.gc.entities.Users;

public interface UsersService {

	public MessageResponse save(Users user);

	public MessageResponse update(Users user);

	public MessageResponse delete(Integer id);

	public List<Users> findAll();

	public Users findById(Integer id);

	public MessageResponse changePassword(PasswordDto passwordDto);
}
