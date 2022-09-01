package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.repository.UserRepository;
import com.service.TokenService;

@RequestMapping("/public")
@RestController
public class SessionController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder bcrypt; 
	
	@Autowired
	TokenService tokenService;

	
	@PostMapping("/signup")
	public ResponseEntity<ResponseBean<UserBean>> signup(@RequestBody UserBean user){
		UserBean dbUser = userRepo.findByEmail(user.getEmail());
		ResponseBean<UserBean> res = new ResponseBean<>();
		if(dbUser ==null) {
			 String encPassword = bcrypt.encode(user.getPassword());
			 user.setPassword(encPassword);
			 userRepo.save(user);
			
			res.setMsg("Signup done");
			
			return ResponseEntity.ok(res);	
		}else {
			res.setData(user);
			res.setMsg("Email Already Taken");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}	
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginBean login){
		UserBean dbUser = userRepo.findByEmail(login.getEmail());
		if(dbUser == null || bcrypt.matches(login.getPassword(),dbUser.getPassword()) == false){
			ResponseBean<UserBean> res = new ResponseBean<>();
			res.setData(dbUser);
			res.setMsg("Invalid credential......");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
		}
		else {
			String authToken = tokenService.generateToken(16);
			dbUser.setAuthToken(authToken);
			userRepo.save(dbUser);
			
			ResponseBean<UserBean> res = new ResponseBean<>();
			res.setData(dbUser);
			res.setMsg("login done");
			return ResponseEntity.ok(res);	
		}
	
	}


}
