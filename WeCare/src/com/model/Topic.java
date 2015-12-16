package com.model;

import java.util.Date;

/**
 * <h1>We Care</h1>
 * This is a model class corresponding to the 
 * <i>topics</i> table in the database.
 * 
 * @version 1.0
 * @since 2015-05-28
 */
public class Topic {
	private int topicID;
	private String topicName;
	private String comment;
	private String uID;
	private Date date;
	
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUID() {
		return uID;
	}
	public void setUID(String uID) {
		this.uID = uID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
