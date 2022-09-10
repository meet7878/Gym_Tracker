package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.PersonalDetailBean;
import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.bean.admin.OfferBean;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.repository.admin.OfferRepository;
	//import com.service.TokenService;

@CrossOrigin
@RequestMapping("/public")
@RestController
public class SessionController {
	@Autowired
	UserRepository userRepo;
	
//	@Autowired
//	PersonalDetailRepository personalRepo;
	
	@Autowired
	BCryptPasswordEncoder bcrypt; 
	
	@Autowired
	RoleRepository roleRepo; 
	
//	@Autowired
//	TokenService tokenService;

	@Autowired
	OfferRepository offerRepo;
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseBean<UserBean>> signup(@RequestBody UserBean user){
		UserBean dbUser = userRepo.findByEmail(user.getEmail());
		ResponseBean<UserBean> res = new ResponseBean<>();
		if(dbUser ==null) {
		    RoleBean role = roleRepo.findByRoleName("user");
			user.setRole(role); 
			
			String encPassword = bcrypt.encode(user.getPassword());
			 user.setPassword(encPassword);
			 userRepo.save(user);
			 
//			 PersonalDetailBean personal = new PersonalDetailBean();
//			 personal.setUsers(user);
//			 
//			 personalRepo.save(personal);
//			 res.setData(user);
//			 System.out.println("meetu"+personal);
			 
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
//			String authToken = tokenService.generateToken(16);
//			dbUser.setAuthToken(authToken);
			userRepo.save(dbUser);
			
			ResponseBean<UserBean> res = new ResponseBean<>();
			res.setData(dbUser);
			res.setMsg("login done");
			return ResponseEntity.ok(res);	
		}
	
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getAllUser() {
		System.out.println("userRepo.findAll()"+"vinu");
//		List<UserBean> user = userRepo.findByUser();
		return ResponseEntity.ok(userRepo.findByUser()); 
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserId(@PathVariable("userId") Integer userId) {
		try {
			System.err.println("only one user.............");
			Optional<UserBean> optional =  userRepo.findById(userId);
			if(optional.isPresent()) {
				UserBean User = optional.get();
				return ResponseEntity.ok(User);
			}
			else {  
				return ResponseEntity.badRequest().build();
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserBean user) {
		try {
			System.err.println("update.......");
			userRepo.save(user);
		} catch (Exception e) {
			System.err.println("errsss.......");
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	
	//offerid pssed on user table one to many relation
	@GetMapping("/offer/{userid}/{offerid}")
	public ResponseEntity<?> updateOfferID(@PathVariable("userid") Integer userId,@PathVariable("offerid")Integer offerId) {
		System.out.println("UPDATE REACHED");
		try {
			System.err.println("update.......");
			UserBean users = userRepo.findByUserId(userId);
			if(users!=null) {
				OfferBean offerBean = offerRepo.findByOfferId(offerId);
				users.setOffers(offerBean);
				userRepo.save(users);
			}
			
			return ResponseEntity.ok().body(users);
		} catch (Exception e) {
			System.err.println("errsss......."+e);
			return ResponseEntity.unprocessableEntity().build();
		}
		
		
	}
	
	
	
	//user with offer
		@GetMapping("/alloffer/{userId}")
		public ResponseEntity<?> getAllUserOffer(@PathVariable("userId") Integer userId){
			try {
				
				System.err.println("offer with user..");
				/* UserBean offer = userRepo.findOffer(userId); */
				UserBean offer=userRepo.findByUserId(userId);
				if(offer!=null) {
					System.out.println(offer.getEmail());
					return ResponseEntity.ok(offer);
				}else {
					return ResponseEntity.badRequest().body(null);
				}
			
			}catch(IllegalArgumentException e) {
		       return ResponseEntity.badRequest().build();
				
			}
		}
//		select o.plan_Name,o.amount from offers as o inner join users as u on u.offer_id= o.offer_id where u.user_id = 1
//		select o.plan_Name,o.amount from offers as o inner join users as u on u.offer_id= o.offer_id
	
//	@PutMapping("/updateoffer/{userid}/{offerid}")
//	public ResponseEntity<?> updateOfferID(@PathVariable("userid") Integer userId,@PathVariable("offerid")Integer offerId) {
//		System.out.println("UPDATE REACHED");
//		try {
//			System.err.println("update.......");
//			UserBean users = userRepo.updateOfferID(userId,offerId);
//			
//			return ResponseEntity.ok().body(users);
//		} catch (Exception e) {
//			System.err.println("errsss......."+e);
//			return ResponseEntity.unprocessableEntity().build();
//		}
//		
//		
//	}

}
