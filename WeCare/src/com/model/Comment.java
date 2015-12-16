package com.model;

import java.util.Date;
/**
 * <h1>We Care</h1>
 * This is a model class corresponding to the 
 * <i>comments</i> table in the database.
 * 
 * @version 1.0
 * @since 2015-05-28
 */
public class Comment {
	private int id;
	private int topicID;
	String userID, comment;
	Date time;
	
	public Comment() {
		
	}
	
	public Comment(int id, int topicID, String userID, String comment, Date time) {
		this.id = id;
		this.topicID = topicID;
		this.userID = userID;
		this.comment = comment;
		this.time = time;
	}
	
	public Comment(int topicID, String userID, String comment, Date time) {
		this.topicID = topicID;
		this.userID = userID;
		this.comment = comment;
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
