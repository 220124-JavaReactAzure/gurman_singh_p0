package com.revature.course_reg.models;

public class Faculty {

		//Variables
		private String facultyId;
		private String firstName;
		private String lastName;
		private String email;
		private String username;
		private String password;

		//Constructors
		
		public Faculty() {
			super();
		}
		
		public Faculty(String firstName, String lastName, String email, String username, String password) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.username = username;
			this.password = password;
		}

		public Faculty(String facultyId, String firstName, String lastName, String email, String username,
				String password) {
			super();
			this.facultyId = facultyId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.username = username;
			this.password = password;
		}

		//Getters and Setters
		public String getFacultyId() {
			return facultyId;
		}

		public void setFacultyId(String facultyId) {
			this.facultyId = facultyId;
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

		@Override
		public String toString() {
			return "Faculty [facultyId=" + facultyId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
					+ email + ", username=" + username + ", password=" + password + "]";
		}
	
}