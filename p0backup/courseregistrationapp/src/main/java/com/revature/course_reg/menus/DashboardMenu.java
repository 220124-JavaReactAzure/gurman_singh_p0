package com.revature.course_reg.menus;

import java.io.BufferedReader;

import com.revature.course_reg.exceptions.InvalidRequestException;
import com.revature.course_reg.models.Course;
import com.revature.course_reg.models.Student;
import com.revature.course_reg.services.CourseService;
import com.revature.course_reg.services.StudentService;
import com.revature.course_reg.util.List;
import com.revature.course_reg.util.MenuRouter;

public class DashboardMenu extends Menu{

	private final StudentService studentService;
	private final CourseService courseService;

	public DashboardMenu(BufferedReader consoleReader, MenuRouter router, StudentService studentService, CourseService courseService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		this.studentService = studentService;
		this.courseService = courseService;
	}

	@Override
	public void render() throws Exception {
		
		Student sessionStudent = studentService.getSessionStudent();
		
		if (sessionStudent == null) {
			System.out.println("You are not currently logged in! Returning to login screen.");
			router.transfer("/login");
			return;
		}
		
		while (studentService.isSessionActive()) {
			System.out.println("Welcome: " + sessionStudent.getUsername());
			String menu = "1) Add class\n" +
							"2) Remove Class\n" +
							"3) View my classes\n" +
							"4) Logout\n";
			System.out.print(menu);
			
			String input = consoleReader.readLine();
			
			switch(input) {
			case "1":
				System.out.println("Please enter the name of the course you would like to add.");
				System.out.print("Course name: ");
				String courseName = consoleReader.readLine();
				try {
					courseService.createCourse(courseName);
				} catch (InvalidRequestException e) {
					System.out.println("Invalid details provided. Please try again.");
					router.transfer("/dashboard");
				}
				break;
			case "2":
				System.out.println("Please enter the name of the course you would like to remove.");
				System.out.print("Course name: ");
				String removedCourseName = consoleReader.readLine();
				courseService.removeCourse(removedCourseName);
				break;
			case "3":
				List<Course> courses = courseService.findMyCourses();
				for (int i = 0; i < courses.size(); i++) {
					System.out.println(courses.get(i));
				}
				break;
			case "4":
				studentService.logout();
			}
		}
	}

}