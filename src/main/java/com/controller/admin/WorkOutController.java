package com.controller.admin;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.admin.WorkOutBean;
import com.repository.admin.WorkoutRepository;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class WorkOutController {
	
	@Autowired
	WorkoutRepository workoutRepo;
	
	@PostMapping("/workout")
	public ResponseEntity<?> addWorkout(@RequestBody WorkOutBean workout){
		workoutRepo.save(workout);
		return ResponseEntity.ok(workout);
	}
	
	@GetMapping("/workout")
	public ResponseEntity<?> getAllWorkout(){
		return ResponseEntity.ok(workoutRepo.findAll());
	}
	
	@DeleteMapping("/workout/{workOutId}")
	public ResponseEntity<?> removeWorkout(@PathVariable("workOutId") Integer workOutId){
		try {
			workoutRepo.deleteById(workOutId);
		}catch(IllegalArgumentException e){
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/workout/{workOutId}")
	public ResponseEntity<?> getWorkoutById(@PathVariable("workOutId") Integer workOutId){
		try {
			Optional<WorkOutBean> optional = workoutRepo.findById(workOutId);
			if(optional.isPresent()) {
				WorkOutBean workout = optional.get();
				return ResponseEntity.ok(workout);
			}else {
				return ResponseEntity.badRequest().build();
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}