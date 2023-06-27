package com.grupo06.parcial2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo06.parcial2.models.entities.Token;
import com.grupo06.parcial2.models.entities.User;


public interface TokenRepository extends JpaRepository<Token, Long>{
	List<Token> findByUserAndActive(User user, Boolean active);
}
