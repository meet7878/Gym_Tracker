package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name="pesrsonaldetails")
public class PersonalDetailBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer personalId;
	
	Integer age;
	Integer height;
	Integer weight;
	Integer fat;
	Integer chest;
	Integer arms;
	String goal;
	String deit;
    String emergencyNum;
    

    @OneToOne
    @JoinColumn(name="userId")
	UserBean user;

    
	public UserBean getUsers() {
		return user;
	}
	public void setUsers(UserBean users) {
		this.user = users;
	}
	public Integer getPersonalId() {
		return personalId;
	}
	public void setPersonalId(Integer personalId) {
		this.personalId = personalId;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getFat() {
		return fat;
	}
	public void setFat(Integer fat) {
		this.fat = fat;
	}
	public Integer getChest() {
		return chest;
	}
	public void setChest(Integer chest) {
		this.chest = chest;
	}
	public Integer getArms() {
		return arms;
	}
	public void setArms(Integer arms) {
		this.arms = arms;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getDeit() {
		return deit;
	}
	public void setDeit(String deit) {
		this.deit = deit;
	}
	public String getEmergencyNum() {
		return emergencyNum;
	}
	public void setEmergencyNum(String emergencyNum) {
		this.emergencyNum = emergencyNum;
	}
    
    
    
    
    
	
}
