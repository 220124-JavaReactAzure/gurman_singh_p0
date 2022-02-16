package com.revature.course_reg.menus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.revature.course_reg.exceptions.InvalidRequestException;
import com.revature.course_reg.exceptions.ResourcePersistenceException;
import com.revature.course_reg.models.Student;
import com.revature.course_reg.services.StudentService;
import com.revature.course_reg.util.MenuRouter;

public class RegisterMenu extends Menu{
	
	StudentService studentService;


	public RegisterMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService) {
		super("Register", "/register", consoleReader, router);
		this.studentService = studentService;
	}

	@Override
	public void render() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("The user selected register");
		
		// Things to obtain from user: first name, last name, email, username, password
		
		System.out.println("Please provide information to register");
		System.out.print("First Name: ");
		String fname = consoleReader.readLine();
		
		System.out.print("Last Name: ");
		String lname = consoleReader.readLine();
		
		System.out.print("Email: ");
		String email = consoleReader.readLine();
		
		System.out.print("Username: ");
		String username = consoleReader.readLine();
		
		System.out.print("Password: ");
		String password = consoleReader.readLine();
		

		Student student = new Student(fname, lname, email, username, password);
		
		System.out.printf("Provided by user: %s\n", student.toString()).println();
		
		try {
			studentService.registerNewStudent(student);
		} catch (InvalidRequestException e ) {
			System.out.println("Invalid data provided, please try again\n\n\n");
			
			router.transfer("/welcome");
		} catch (ResourcePersistenceException e ) {
			System.out.println("Persistence issue occurred, please try again\n\n\n");
			
			router.transfer("/welcome");
		}
	}

}