package com.bean.admin;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bean.UserBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="offers")
public class OfferBean {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Integer offerId;

String planName;
String planType;
Integer amount;
Integer duration;



@JsonIgnore
@OneToMany(mappedBy = "offers")
Set<UserBean> users;	

	 
//@ManyToOne
//@JoinColumn(name="userId",nullable=true)
//UserBean users;








}
