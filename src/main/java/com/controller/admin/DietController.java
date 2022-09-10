package com.controller.admin;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bean.DietBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.repository.admin.DietRepository;
import com.repository.UserRepository;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class DietController {
	@Autowired
	DietRepository dietRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/diet")
	public ResponseEntity<?> addDiet(@RequestBody DietBean diet){
		dietRepo.save(diet);
		return ResponseEntity.ok(diet);
	}
	
	@GetMapping("/diet")
	public ResponseEntity<?> getAllDiet(){
		return ResponseEntity.ok(dietRepo.findAll());
	}
	
	@GetMapping("/diet/{dietId}")
	public ResponseEntity<?> getRoleById(@PathVariable("dietId") Integer dietId){
		try {
			Optional<DietBean> optional = dietRepo.findById(dietId);
			if(optional.isPresent()) {
				DietBean diet = optional.get();
				return ResponseEntity.ok(diet);
			}else {
				return ResponseEntity.badRequest().build();
			}
		}catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/diet/{dietId}")
	public ResponseEntity<?> removeDiet(@PathVariable("dietId") Integer dietId){
		try {
			dietRepo.deleteById(dietId);
		}catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/diet")
	public ResponseEntity<?> updateDiet(@RequestBody DietBean diet){
		try {
			dietRepo.save(diet);
		}catch(Exception e) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(diet);
	}
}