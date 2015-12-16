package com.model;

import java.util.Date;

/**
 * <h1>We Care</h1>
 * This is a model class corresponding to the 
 * <i>events</i> table in the database.
 * 
 * @version 1.0
 * @since 2015-05-28
 */
public class Event {
	private int id;
	private String name;
	private String description;
	private Date time;
	private String venue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
}
