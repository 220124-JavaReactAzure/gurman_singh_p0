package com.revature.course_reg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.revature.course_reg.models.Student;
import com.revature.course_reg.util.AppState;


public class CoReDriver {
	
	public static void main(String[] args) {
		AppState app = new AppState();
		app.startup();
	}
	
}