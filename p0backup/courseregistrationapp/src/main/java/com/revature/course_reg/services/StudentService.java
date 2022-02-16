package com.revature.course_reg.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.revature.course_reg.daos.StudentDAO;
import com.revature.course_reg.exceptions.AuthenticationException;
import com.revature.course_reg.exceptions.InvalidRequestException;
import com.revature.course_reg.exceptions.ResourcePersistenceException;
import com.revature.course_reg.models.Student;
import com.revature.course_reg.util.List;

public class StudentService {

	private final StudentDAO studentDao;
	private Student sessionStudent;
	
	public StudentService(StudentDAO studentDAO) {
		this.studentDao = studentDAO;
		this.sessionStudent = null;
	}
	
	public Student getSessionStudent() {
		return sessionStudent;
	}
	
	public Student registerNewStudent(Student newStudent) {
		if(!isStudentValid(newStudent)) {
			throw new InvalidRequestException("Invalid user data provided");
		}
		
		boolean usernameAvailable = studentDao.findByUsername(newStudent.getUsername()) == null;
		boolean emailAvailable = studentDao.findByEmail(newStudent.getEmail()) == null;
		
		if(!usernameAvailable || !emailAvailable) {
			if(!usernameAvailable && emailAvailable) {
				throw new ResourcePersistenceException("The provided username was already taken in the database");
			} else if(usernameAvailable) {
				throw new ResourcePersistenceException("The provided email was already taken in the database");
			} else {
				throw new ResourcePersistenceException("The provided username and email were already taken in the database");
			}
		}
		
		Student persistedStudent = studentDao.create(newStudent);
		
		if(persistedStudent == null) {
			throw new ResourcePersistenceException("The student could not be persisted");
		}
		
		return persistedStudent;
	}
	
	public List<Student> getAllStudents() {
		
		return studentDao.findAll();
	
	}
	
	
	public void authenticateStudent(String username, String password) {
		
		if(username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
		}
		
		Student authenticatedStudent = studentDao.findByUsernameAndPassword(username, password);
		
		if(authenticatedStudent == null) {
			throw new AuthenticationException("Unauthenticated user, information provided was not found in our database.");
		}
		
		sessionStudent = authenticatedStudent;
	}
	
	public boolean isStudentValid(Student newStudent) {
		if(newStudent == null) return false;
		if(newStudent.getFirstName() == null || newStudent.getFirstName().trim().equals("")) return false;
		if(newStudent.getLastName() == null || newStudent.getLastName().trim().equals("")) return false;
		if(newStudent.getEmail() == null || newStudent.getEmail().trim().equals("")) return false;
		if(newStudent.getUsername() == null || newStudent.getUsername().trim().equals("")) return false;
		return newStudent.getPassword() != null || newStudent.getPassword().trim().equals("");
	}
	
	public void logout() {
		sessionStudent = null;
	}
	
	public boolean isSessionActive() {
		return sessionStudent != null;
	}
}