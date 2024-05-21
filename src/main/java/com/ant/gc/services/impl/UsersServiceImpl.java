package com.ant.gc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ant.gc.dto.MessageResponse;
import com.ant.gc.dto.PasswordDto;
import com.ant.gc.entities.Users;
import com.ant.gc.repositories.ContactRepository;
import com.ant.gc.repositories.UsersRepository;
import com.ant.gc.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public MessageResponse save(Users user) {

		boolean exist = contactRepository.existsByEmail(user.getEmail());

		if (exist) {
			return new MessageResponse(false, "Attention", "Email existe déjà!!");
		}

		exist = usersRepository.existsByUsername(user.getUsername());

		if (exist) {
			return new MessageResponse(false, "Attention", "Nom d'utilisateur existe déjà!!");
		}

		String cryptedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(cryptedPassword);

		usersRepository.save(user);
		return new MessageResponse(true, "Succès", "Opération effectuée");
	}

	@Transactional

	@Override
	public MessageResponse update(Users user) {
		Users us = findById(user.getId());
		if (us == null) {
			return new MessageResponse(false, "Attention", "Utilisteaur introuvable!!");
		}
		boolean exist = contactRepository.existsByEmailAndId(user.getEmail(), user.getId());

		if (!exist) {
			exist = contactRepository.existsByEmail(user.getEmail());

			if (exist) {
				return new MessageResponse(false, "Attention", "Email existe déjà!!");
			}
		}

		exist = usersRepository.existsByUsernameAndId(user.getUsername(), user.getId());
		if (!exist) {

			exist = usersRepository.existsByUsername(user.getUsername());
			if (exist) {
				return new MessageResponse(false, "Attention", "Nom d'utilisateur existe déjà!!");
			}
		}

		usersRepository.save(user);
		return new MessageResponse(true, "Succès", "Opération effectuée");
	}

	@Transactional
	@Override
	public MessageResponse delete(Integer id) {
		Users user = findById(id);
		if (user == null) {
			return new MessageResponse(false, "Attention", "Utilisteaur introuvable!!");
		}

		usersRepository.delete(user);

		return new MessageResponse(true, "Succès", "Opération effectuée");

	}

	@Transactional(readOnly = true)
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Users findById(Integer id) {
		// TODO Auto-generated method stub
		return usersRepository.findById(id).orElse(null);
	}

	@Override
	public MessageResponse changePassword(PasswordDto passwordDto) {

		Users user = findById(passwordDto.getId());
		if (user == null) {
			return new MessageResponse(false, "Attention", "Utilisateur introuvable");
		}

		boolean valid = passwordEncoder.matches(passwordDto.getOldPassword(), 
				user.getPassword());

		if (!valid) {
			return new MessageResponse(false, "Attention", "Ancien mot de passe inccorect");
		}
		
		String cryptedPassword = passwordEncoder.encode(passwordDto.getNewPassword());
		user.setPassword(cryptedPassword);
		usersRepository.save(user);
		return new MessageResponse(true, "Succès", "Opération effectuée");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Users user  = usersRepository.findOneByUsername(username).orElse(null);
		
		
		return user;
	}

}
