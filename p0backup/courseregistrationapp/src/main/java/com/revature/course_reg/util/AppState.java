package com.revature.course_reg.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.course_reg.daos.CourseDAO;
import com.revature.course_reg.daos.StudentDAO;
import com.revature.course_reg.menus.DashboardMenu;
import com.revature.course_reg.menus.LoginMenu;
import com.revature.course_reg.menus.RegisterMenu;
import com.revature.course_reg.menus.WelcomeMenu;
import com.revature.course_reg.services.CourseService;
import com.revature.course_reg.services.StudentService;

public class AppState {

	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		StudentDAO studentDAO = new StudentDAO();
		StudentService studentService = new StudentService(studentDAO);
		CourseDAO courseDAO = new CourseDAO();
		CourseService courseService = new CourseService(courseDAO, studentService);
		router.addMenu(new WelcomeMenu(consoleReader,router));
		router.addMenu(new LoginMenu(consoleReader, router, studentService));
		router.addMenu(new DashboardMenu(consoleReader, router, studentService, courseService));
		router.addMenu(new RegisterMenu(consoleReader,router, studentService));
	}
	
	public void startup() {
		try {
			while(isRunning) {
				router.transfer("/welcome");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {
		isRunning = false;
	}
}