 package com.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.PersonalDetailBean;

import com.repository.PersonalDetailRepository;
import com.repository.UserRepository;

@CrossOrigin
@RequestMapping("/public")
@RestController
public class PersonalDetailController {
	
	@Autowired
	PersonalDetailRepository personalRepo;
	@Autowired
	UserRepository userRepo;
	

	@PostMapping("/personal")
	public ResponseEntity<?> addPersonalDetail(@RequestBody PersonalDetailBean personal){
		
		
		personalRepo.save(personal);
		return ResponseEntity.ok(personal);
	}
	
	
	@GetMapping("/personal/{userId}")
	public ResponseEntity<?> getPersonalUserId(@PathVariable("userId") Integer userId){
		try {
			
			System.err.println("one personal one user..");
			PersonalDetailBean optional = personalRepo.findByUser(userId);
			if(optional!=null) {
				PersonalDetailBean personal = optional;
				return ResponseEntity.ok(personal);
			}else {
				return ResponseEntity.badRequest().build();
			}
		
		}catch(IllegalArgumentException e) {
	       return ResponseEntity.badRequest().build();
			
		}
	}
	

}
