package com.ant.gc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.gc.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	boolean existsByUsername(String username);
	boolean existsByUsernameAndId(String username, Integer id);
//	@Query("select u from Users where u.nom=:nom")
//	public List<Users> trouverParNom(@Param("nom") String nom);
	Optional<Users> findOneByUsername(String username);
}
