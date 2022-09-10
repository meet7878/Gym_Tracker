package com.bean;

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

import com.bean.admin.OfferBean;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name ="users")
public class UserBean {
 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userId;
	
    String firstName;
    String lastName;
    String number;
    String Date;
    String address;
    Integer pincode;
    String email;
    
    String password;
    String gender;
    String authToken;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user")   
    PersonalDetailBean pesrsonaldetails;
    
    
  
	public PersonalDetailBean getPesrsonaldetails() {
		return pesrsonaldetails;
	}
	public void setPesrsonaldetails(PersonalDetailBean pesrsonaldetails) {
		this.pesrsonaldetails = pesrsonaldetails;
	}
	@ManyToOne
	@JoinColumn(name = "roleId" , nullable= false)
	RoleBean role;
    	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	
	@JsonIgnore
    @OneToMany(mappedBy = "users")
    Set<DietBean> diet;
	
	public Set<DietBean> getDiet() {
		return diet;
	}
	public void setDiet(Set<DietBean> diet) {
		this.diet = diet;
	}
	
	
	
	 
@ManyToOne
@JoinColumn(name="offerId",nullable=true)
OfferBean offers;



public OfferBean getOffers() {
	return offers;
}
public void setOffers(OfferBean offers) {
	this.offers = offers;
}
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "users")
//	Set<OfferBean> offers;	
	

//	@OneToMany
//	@JoinColumn(name="offerId",nullable=true)
//	Set<OfferBean> offers;
	


	
	
	
  
    
    

}
