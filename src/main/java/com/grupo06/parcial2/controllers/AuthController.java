package com.grupo06.parcial2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo06.parcial2.models.dto.LoginDTO;
import com.grupo06.parcial2.models.dto.MessageDTO;
import com.grupo06.parcial2.models.dto.TokenDTO;
import com.grupo06.parcial2.models.dto.UserInfoDTO;
import com.grupo06.parcial2.models.entities.User;
import com.grupo06.parcial2.services.UserService;
import com.grupo06.parcial2.utils.TokenManager;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenManager tokenManager;
	
	@GetMapping("/signup")
	public String getRegisterPage(Model model) {
		model.addAttribute("register", new UserInfoDTO("", "", ""));
		return "register";
	}
	
	@PostMapping("/signup")
	public String registerUser(
			@ModelAttribute(name="register")
			@Valid UserInfoDTO userInfo,
			BindingResult result) {
		try {
			if(result.hasErrors()) {
				if(result.hasErrors()) {
					return "badrequest";
				}
			}
			
			User foundUser = userService.findOneByUsernameAndEmail(userInfo.getUsername(),userInfo.getEmail());
			
			if(foundUser != null) {
				return "alreadyregistered";
			}
			
			userService.register(userInfo);
			
			return "registered";
			
		} catch (Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/signin")
	public String getLoginPage(Model model) {
		model.addAttribute("login", new LoginDTO("", ""));
		return "login";
	}
	
	@PostMapping("/signin")
	public ResponseEntity<TokenDTO> login(
			@ModelAttribute(name="login")
			@Valid LoginDTO loginInfo,
			BindingResult result) {
			try {
				
				if(result.hasErrors()) {
					return new ResponseEntity<>(
							new TokenDTO(),
							HttpStatus.BAD_REQUEST
						);
				}
				
				User user = userService.findOneByIdentifer(loginInfo.getIdentifier());
				
				if(!userService.comparePassword(user, loginInfo.getPassword())) {
					return new ResponseEntity<>(
							new TokenDTO(),
							HttpStatus.UNAUTHORIZED
						);
				}
				
				final String token = tokenManager.generateJwtToken(user.getUsername());
				
				userService.insertToken(user, token); 
				
				return new ResponseEntity<>(
						new TokenDTO(token),
						HttpStatus.CREATED
					);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<>(
						new TokenDTO(),
						HttpStatus.INTERNAL_SERVER_ERROR
					);
			}
	}
	
}
