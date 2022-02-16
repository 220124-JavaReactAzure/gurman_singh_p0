package com.revature.course_reg.menus;

import java.io.BufferedReader;

import com.revature.course_reg.util.MenuRouter;
import static com.revature.course_reg.util.AppState.shutdown;

public class WelcomeMenu extends Menu {

	public WelcomeMenu(BufferedReader consoleReader, MenuRouter router) {
		super("Welcome", "/welcome", consoleReader, router);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() throws Exception {
		
		System.out.println(
				"Welcome to the Singh Course Registration console!\n" +
				"1. Login\n" +
				"2. Register\n" +
				"3. Exit\n" +
				"---- ");
		
		String input = consoleReader.readLine();
		
		switch (input) {
		case "1":
			router.transfer("/login");
			break;
		case "2":
			router.transfer("/register");
			break;
		case "3":
			shutdown();
			break;
		default:
			System.out.println("Invalid selection. Please select one of the listed options.");
			break;
		}
		
		System.out.println("User selected: " + input);

	}

}