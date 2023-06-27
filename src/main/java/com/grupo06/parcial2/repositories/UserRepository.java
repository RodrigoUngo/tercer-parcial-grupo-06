package com.grupo06.parcial2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo06.parcial2.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findOneByUsernameOrEmail(String username, String email);
	User  findOneByUsername(String username);
}
