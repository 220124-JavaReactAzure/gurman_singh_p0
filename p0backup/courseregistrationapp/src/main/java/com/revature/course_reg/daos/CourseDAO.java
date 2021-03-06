package com.revature.course_reg.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.course_reg.models.Course;
import com.revature.course_reg.models.Student;
import com.revature.course_reg.util.ConnectionFactory;
import com.revature.course_reg.util.LinkedList;
import com.revature.course_reg.util.List;

public class CourseDAO implements CrudDAO<Course> {

	//faculty creating classes
	@Override
	public Course create(Course newCourse) {
		// TODO Auto-generated method stub
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "insert into courses (course_name, creator) values (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,  newCourse.getCourseName());
			ps.setString(2,  newCourse.getCreatorID());
			
			int rowsInserted = ps.executeUpdate();
			
			if (rowsInserted != 0) {
				return newCourse;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//shows all courses
	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		List<Course> courseList = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from courses";
			Statement s = conn.createStatement();

			ResultSet resultSet = s.executeQuery(sql);

			while (resultSet.next()) {
				Course course = new Course();
				course.setCourseName(resultSet.getString("course_name"));
				course.setCreatorID(resultSet.getString("creator"));

				courseList.add(course);
			}

			return courseList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Course findById(String student_id) {
		return null;
	}

	@Override
	public boolean update(Course updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	//faculty remove their course from catalog, unregisters students by cascade in table
	@Override
	public boolean delete(String course_name) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "delete from courses where course_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course_name);

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void studentDropCourse(String student_id, String course_name) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "delete from enrolled where course_name = ? and student_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course_name);
			ps.setString(2, student_id);

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//faculty find all courses they teach
	public List<Course> findAllByID(String student_id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			List<Course> courses = new LinkedList<>();

			String sql = "select * from courses where creator = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseName(rs.getString("course_name"));
				course.setCreatorID(rs.getString("creator"));

				courses.add(course);
			}

			return courses;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	//students joining course
	public void enroll(Course course) {
			try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "insert into enrolled (course_name, student_id) values (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,  course.getCourseName());
			ps.setString(2,  course.getCreatorID());
			
			int rowsInserted = ps.executeUpdate();
			
			if (rowsInserted != 0) {
				System.out.println("Successfully enrolled in " + course.getCourseName());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//students find all courses they've enrolled in
	public List<Course> studentFindAllByID(String student_id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			List<Course> courses = new LinkedList<>();

			String sql = "select * from enrolled where student_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseName(rs.getString("course_name"));

				courses.add(course);
			}

			return courses;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
