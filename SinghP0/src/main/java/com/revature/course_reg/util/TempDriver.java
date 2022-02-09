package com.revature.course_reg.util;

public class TempDriver {

	public static void main(String[] args) {
		List<String> myStringList = new LinkedList<>();
		
		myStringList.add("Hello");
		myStringList.add("There");
		myStringList.add("Ahhh");
		myStringList.add("General");
		myStringList.add("Kenobi");
		
		System.out.println(myStringList.contains("Hello")); //false
		System.out.println(myStringList.contains("There")); //false
		System.out.println(myStringList.contains("Ah")); //false
		System.out.println(myStringList.contains("General")); //false
		System.out.println(myStringList.contains("Kenobi")); //false
	}

}
