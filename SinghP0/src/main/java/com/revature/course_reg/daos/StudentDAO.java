package com.revature.course_reg.daos;

import java.io.File;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.revature.course_reg.models.Student;
import com.revature.course_reg.util.ConnectionFactory;
import com.revature.course_reg.util.List;
import com.revature.course_reg.util.LinkedList;

public class StudentDAO implements CrudDAO<Student> {

	public Student findByUsernameAndPassword(String username, String password) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from students where username = ? and password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));

				return student;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Student findByEmail(String email) {
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from students where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("scientist_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));

				return student;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		
		return null;
	}
	
	public Student findByUsername(String username) {
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from scientists where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));

				return student;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Student create(Student newStudent) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			newStudent.setStudentId(UUID.randomUUID().toString());
			
			String sql = "insert into students (student_id, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?) ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newStudent.getStudentId());
			ps.setString(2, newStudent.getFirstName());
			ps.setString(3, newStudent.getLastName());
			ps.setString(4, newStudent.getEmail());
			ps.setString(5, newStudent.getUsername());
			ps.setString(6, newStudent.getPassword());
			
			int rowsInserted = ps.executeUpdate();
			if(rowsInserted != 0) {
				return newStudent;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public List<Student> findAll() {
		
		List<Student> studentsList = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from students";
			Statement s = conn.createStatement();
			
			ResultSet resultSet = s.executeQuery(sql);
			
			while(resultSet.next()) {
				Student student = new Student();
				student.setStudentId(resultSet.getString("student_id"));
				student.setFirstName(resultSet.getString("first_name"));
				student.setLastName(resultSet.getString("last_name"));
				student.setEmail(resultSet.getString("email"));
				student.setUsername(resultSet.getString("username"));
				student.setPassword(resultSet.getString("password"));
				
				studentsList.add(student);
			}
			
			return studentsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Student findById(String id) {
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from scientists where student_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getString("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
				student.setUsername(rs.getString("username"));
				student.setPassword(rs.getString("password"));

				return student;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(Student updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}


}