package com.revature.course_reg.menus;

import java.io.BufferedReader;

import com.revature.course_reg.util.MenuRouter;
import com.revature.course_reg.exceptions.AuthenticationException;
import com.revature.course_reg.services.StudentService;

public class LoginMenu extends Menu{
	
	private final StudentService studentService;


	public LoginMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService) {
		super("Login", "/login", consoleReader, router);
		this.studentService = studentService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("Please enter your credentials for your account.");
		System.out.print("Username: ");
		String username = consoleReader.readLine();
		System.out.print("Password: ");
		String password = consoleReader.readLine();
	
//		System.out.println(studentService.getAllStudents());
		try {
			studentService.authenticateStudent(username, password);
			router.transfer("/dashboard");
		} catch (AuthenticationException e) {
			System.out.println("Incorrect credentials provided! No matching user account found.");
		}
		
	}

}