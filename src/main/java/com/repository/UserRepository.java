package com.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;
import java.util.List;

@Repository

public interface UserRepository extends CrudRepository<UserBean,Integer> {
	
	List<UserBean> findAll();
	
	@Query(value="select * from users where role_id=1",nativeQuery =true )
	List<UserBean> findByUser();
	
	UserBean findByEmail(String email);
	//UserBean  findByUserId(Integer userId);
	//UserBean findByAuthToken(String authToken);
	
//	@Query(value = "update users set offer_id = :offerId where user_id = :userId",nativeQuery = true)
//	UserBean updateOfferID(Integer userId,Integer offerId);
//
	@Query(value="select * from users where user_id=?",nativeQuery=true)
	UserBean findByUserId(Integer userId);
	
	/*
	 * @Query(
	 * value="select o.plan_Name,o.amount from offers as o inner join users as u on u.offer_id= o.offer_id where u.user_id = ?1"
	 * ,nativeQuery =true) UserBean findBy (Integer userId);
	 */
}
 