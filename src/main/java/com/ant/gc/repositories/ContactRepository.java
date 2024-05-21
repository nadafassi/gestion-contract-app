package com.ant.gc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.gc.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	boolean existsByEmail(String email);

	boolean existsByEmailAndId(String email, Integer id);
}
