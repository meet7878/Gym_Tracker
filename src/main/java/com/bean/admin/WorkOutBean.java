package com.bean.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bean.UserBean;

import lombok.Data;

@Data
@Entity
@Table(name="workouts")
public class WorkOutBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer workOutId;
	
	String title;
	String warmUp;
	String exercise;
	String streching;
	String day;
	
	@ManyToOne
	@JoinColumn(name="userId")
	UserBean users;
}
