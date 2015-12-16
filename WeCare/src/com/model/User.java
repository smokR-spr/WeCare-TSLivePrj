package com.model;

/*import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;*/

/**
 * <h1>We Care</h1>
 * This is a model class corresponding to the 
 * <i>user</i> table in the database.
 * 
 * @version 1.0
 * @since 2015-05-28
 */
public class User {
	private String name, gender, city, uID, password, email; //, securityQuestion, securityAnswer;
	//Blob photo;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
/*	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}*/

	/*@JsonIgnore
	public Blob getPhoto() {
		return photo;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
