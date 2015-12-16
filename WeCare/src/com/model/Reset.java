package com.model;

import java.util.Date;

public class Reset {
	String uid;
	int token;
	Date resetTime;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
	public Date getResetTime() {
		return resetTime;
	}
	public void setResetTime(Date resetTime) {
		this.resetTime = resetTime;
	}
	
}
