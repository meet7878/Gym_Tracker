package com.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.PersonalDetailBean;



@Repository
public interface PersonalDetailRepository extends CrudRepository<PersonalDetailBean,Integer> {
 
//	List<PersonalDetailBean> findAll();
//	PersonalDetailBean  findByPersonalId(String pesronalId); 
	
	@Query(value = "select * from pesrsonaldetails where user_id= :userId",nativeQuery = true)
	PersonalDetailBean findByUser(Integer userId); 
	
	
	
	
}
