package com.revature.course_reg.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.course_reg.models.Student;
import com.revature.course_reg.daos.StudentDAO;
import com.revature.course_reg.exceptions.AuthenticationException;
import com.revature.course_reg.exceptions.InvalidRequestException;

public class StudentServiceTestSuite {

	StudentService sut;
	
	@Before
	public void testPrep() {
		StudentDAO studentDAO = new StudentDAO();
		sut = new StudentService(studentDAO);
	}
	
	@Test
	public void test_isStudentValid_returnsTrue_givenValidUser() {
		
		// Arrange
		Student validStudent = new Student("valid","valid","valid","valid","valid");
		
		// Act
		boolean actualResult = sut.isStudentValid(validStudent);
		
		// Assert
		Assert.assertTrue(actualResult);
		
	}
	
	@Test
	public void test_isStudentValid_returnsFalse_givenUserWithFirstName() {
		
		// Arrange
		Student invalidStudent1 = new Student("","valid","valid","valid","valid");
		Student invalidStudent2 = new Student(null,"valid","valid","valid","valid");
		Student invalidStudent3 = new Student("          ","valid","valid","valid","valid");
		
		// Act
		boolean actualResult1 = sut.isStudentValid(invalidStudent1);
		boolean actualResult2 = sut.isStudentValid(invalidStudent2);
		boolean actualResult3 = sut.isStudentValid(invalidStudent3);
		
		// Assert
		Assert.assertFalse(actualResult1);
		Assert.assertFalse(actualResult2);
		Assert.assertFalse(actualResult3);
		
	}
	
	@Test
	public void test_registerStudent_returnsTrue_givenValidStudent() {
 
		//Arrange
		Student validStudent = new Student("valid", "valid", "valid","valid", "valid");
	 
		//Act
		Student actualResult = sut.registerNewStudent(validStudent);
		 
		//Assert
		Assert.assertNotNull(actualResult); }
	 
	@Test(expected = InvalidRequestException.class)
	public void test_registerStudent_throwsInvalidRequestException_givenInvalidUser() {
		sut.registerNewStudent(null);
	}
	 
	@Test
	public void test_authenticateStudent_entersSession_givenValidStudent() {
		
		//Arrange
		Student sessionStudent;
		String username = "gus";
		String password = "guspass";
		
		//Act
		sut.authenticateStudent(username, password);
		
		//Assert
		Assert.assertEquals(sut.getSessionStudent().getUsername(), "gus");
		Assert.assertEquals(sut.getSessionStudent().getPassword(), "guspass");
	}
	
	@Test(expected = AuthenticationException.class)
	public void test_authenticateStudent_throwsAuthenticationException_givenInvalidUser() {
		sut.authenticateStudent("invalid","invalid");
	}
}