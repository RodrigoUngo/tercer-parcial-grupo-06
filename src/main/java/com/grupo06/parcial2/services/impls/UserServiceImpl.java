package com.grupo06.parcial2.services.impls;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grupo06.parcial2.models.dto.UserInfoDTO;
import com.grupo06.parcial2.models.entities.Token;
import com.grupo06.parcial2.models.entities.User;
import com.grupo06.parcial2.repositories.TokenRepository;
import com.grupo06.parcial2.repositories.UserRepository;
import com.grupo06.parcial2.services.UserService;
import com.grupo06.parcial2.utils.TokenManager;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void register(UserInfoDTO userInfo) throws Exception{
		User user = new User();
		
		String encryptedPassword = passEncoder.encode(userInfo.getPassword());
		
		user.setUsername(userInfo.getUsername());
		user.setEmail(userInfo.getEmail());
		user.setPassword(encryptedPassword);
		
		userRepository.save(user);
	}
	
	@Override
	public User findOneByUsernameAndEmail(String username, String email) throws Exception {
		User foundUser = userRepository	
				.findOneByUsernameOrEmail(username, email);
		
		return foundUser;
	}

	@Override
	public User findOneByIdentifer(String identifier) throws Exception {
		
		User foundUser = userRepository	
				.findOneByUsernameOrEmail(identifier, identifier);
		
		return foundUser;
	}
	

	@Override
	public Boolean comparePassword(User user, String passToCompare) throws Exception {
		return passEncoder.matches(passToCompare, user.getPassword());
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insertToken(User user, String token) throws Exception {
		cleanTokens(user);
		
		Token newToken = new Token(token, user);
		tokenRepository.save(newToken);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Boolean isTokenValid(User user, String token) throws Exception {
		cleanTokens(user);
		
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
				
		return tokens.stream()
		.filter((userToken) -> {
			return userToken.getContent().equals(token) && userToken.getActive();
		})
		.findAny()
		.orElse(null) != null;
	}
	
	@Transactional(rollbackOn = Exception.class)
	private void cleanTokens(User user) {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		
		tokens.forEach((userToken) -> {
			if(!tokenManager.validateJwtToken(userToken.getContent(), user.getUsername())) {
				userToken.setActive(false);
				tokenRepository.save(userToken);
			}
		});
	}

	@Override
	public User getUserAuthenticated() throws Exception {
		String username = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		return userRepository.findOneByUsername(username);
	}
}
