package com.bean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="diet")
public class DietBean {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer dietId;
	
	String dietFood;
	String time;
	
	@ManyToOne
	@JoinColumn(name ="userId")
	UserBean users;
}