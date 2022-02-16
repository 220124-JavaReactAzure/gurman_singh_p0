package com.revature.course_reg.services;

import com.revature.course_reg.services.StudentService;
import com.revature.course_reg.services.CourseService;
import com.revature.course_reg.daos.StudentDAO;
import com.revature.course_reg.models.Course;
import com.revature.course_reg.models.Student;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.course_reg.daos.CourseDAO;


public class CourseServiceTestSuite {

	StudentService sut;
	StudentDAO studentDAO;
	CourseService cut;
	CourseDAO courseDAO;
	
	@Before
	public void testPrep() {
		StudentDAO studentDAO = new StudentDAO();
		sut = new StudentService(studentDAO);
		CourseDAO courseDAO = new CourseDAO();
		cut = new CourseService(courseDAO, sut);
	}
	
	@Test
	public void test_isCourseValid_returnsTrue_givenValidName() {
		
		// Arrange
		Course validCourse = new Course("valid", "valid");
		
		// Act
		boolean actualResult = cut.isCourseValid(validCourse);
		
		// Assert
		Assert.assertTrue(actualResult);
		
	}
	
	@Test
	public void test_isCourseValid_returnsFalse_givenCourseWithInvalidName() {
		
		// Arrange
		Course invalidCourse1 = new Course("", "valid");
		Course invalidCourse2 = new Course(null, "valid");
		Course invalidCourse3 = new Course("       ", "valid");
		
		// Act
		boolean actualResult1 = cut.isCourseValid(invalidCourse1);
		boolean actualResult2 = cut.isCourseValid(invalidCourse2);
		boolean actualResult3 = cut.isCourseValid(invalidCourse3);
		
		// Assert
		Assert.assertFalse(actualResult1);
		Assert.assertFalse(actualResult2);
		Assert.assertFalse(actualResult3);
	}
}
