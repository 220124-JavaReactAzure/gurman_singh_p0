package com.revature.course_reg.models;

public class Student {

	// Attributes/Variables
		private String studentId;
		private String firstName;
		private String lastName;
		private String email;
		private String username;
		private String password;
		private boolean isFaculty = false;

		public Student() {
			super();
		}
		
		// Constructor
		public Student(String firstName, String lastName, String email, String username, String password) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.username = username;
			this.password = password;
		}

		public Student(String studentId, String firstName, String lastName, String email, String username,
				String password) {
			super();
			this.studentId = studentId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.username = username;
			this.password = password;
		}
		
		public Student(String studentId, String firstName, String lastName, String email, String username,
				String password, boolean isFaculty) {
			super();
			this.studentId = studentId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.username = username;
			this.password = password;
			this.isFaculty = false;
		}

		
		// Getters and Setters
		public String getStudentId() {
			return studentId;
		}

		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean getIsFaculty() {
			return isFaculty;
		}

		public void setIsFaculty(boolean isFaculty) {
			this.isFaculty = isFaculty;
		}
		
		//Custom Methods
		public String toFileString() {
			StringBuilder buildFileString = new StringBuilder();
			
			buildFileString.append(firstName).append(":")
							.append(lastName).append(":")
							.append(email).append(":")
							.append(username).append(":")
							.append(password);
			
			return buildFileString.toString();
		}
		
		public void printFromFile(String[] arr) {
			for(String arrV : arr) {
				System.out.println(arrV);
			}
		}
		
		//Overrides come last
		@Override
		public String toString() {
			return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
					+ email + ", username=" + username + ", password=" + password + "]";
		}
	
}