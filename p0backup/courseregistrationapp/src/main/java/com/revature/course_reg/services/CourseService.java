package com.revature.course_reg.services;

import com.revature.course_reg.daos.CourseDAO;
import com.revature.course_reg.exceptions.InvalidRequestException;
import com.revature.course_reg.exceptions.ResourcePersistenceException;
import com.revature.course_reg.models.Course;
import com.revature.course_reg.util.List;

public class CourseService {

	private final CourseDAO courseDAO;
	private final StudentService studentService;
	
	public CourseService(CourseDAO courseDAO, StudentService studentService) {
		this.courseDAO = courseDAO;
		this.studentService = studentService;
	}
	
	
	public void createCourse(String courseName) {
		
		Course newCourse = new Course(courseName, studentService.getSessionStudent().getStudentId());
		
		if(!isCourseValid(newCourse)) {
			throw new InvalidRequestException("The course was provided invalid information");
		}
		
		if (studentService.getSessionStudent().getIsFaculty()) {
			newCourse.setCreatorID(studentService.getSessionStudent().getStudentId());
			Course createdCourse = courseDAO.create(newCourse);
			
			if(createdCourse == null) {
				throw new ResourcePersistenceException("The course could not be persisted");
			}
		} else {
			courseDAO.enroll(newCourse);
		}
	}
	
	public boolean isCourseValid(Course newCourse) {

		if (newCourse == null) {
			return false;
		}
		return !(newCourse.getCourseName() == null || newCourse.getCourseName().trim().equals(""));
	}
	
	public List<Course> findMyCourses(){
		if (studentService.getSessionStudent().getIsFaculty()) {
			return courseDAO.findAllByID(studentService.getSessionStudent().getStudentId());
		}
		return courseDAO.studentFindAllByID(studentService.getSessionStudent().getStudentId());
	}
	
	public List<Course> findAllCourses() {
		return null;
	}
	
	public void removeCourse(String courseName) {
		if (studentService.getSessionStudent().getIsFaculty()) {
			courseDAO.delete(courseName);
		} else {
			courseDAO.studentDropCourse(studentService.getSessionStudent().getStudentId(), courseName);
		}
	}
}
