package com.revature.course_reg.models;

public class Course {

	private String courseName;
	private String creator_id;
	
	
	//Constructors
	public Course(String courseName, String creator_id) {
		super();
		this.courseName = courseName;
		this.creator_id = creator_id;
	}
	
	public Course() {
		super();
	}
	
	
	//Getters and Setters
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCreatorID() {
		return creator_id;
	}
	
	public void setCreatorID(String creator_id) {
		this.creator_id = creator_id;
	}
	
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + "]";
	}
}