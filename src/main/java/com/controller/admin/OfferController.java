package com.controller.admin;

import java.util.List;

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
import com.bean.admin.OfferBean;
import com.repository.admin.OfferRepository;

@CrossOrigin
@RestController
@RequestMapping("/plan")
public class OfferController {
  
	@Autowired
	OfferRepository offerRepo;
	
	@PostMapping("/offer")
	public ResponseEntity<?> addOffer(@RequestBody OfferBean offer){
		offerRepo.save(offer);
		return ResponseEntity.ok(offer);
	}
	
	
	
	@GetMapping("/alloffer")
	public ResponseEntity<?> getAllOffer() {
		System.out.println("offerRepo.findAll()"+"vinu");
		List<OfferBean> offer = (List<OfferBean>) offerRepo.findAll();
		return ResponseEntity.ok(offer); 
	}
	
	
	
	
}
