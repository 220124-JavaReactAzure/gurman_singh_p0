package com.revature.course_reg.daos;

import com.revature.course_reg.util.List;

public interface CrudDAO<T> {
	
	
	// CRUD: Creat, Read, Update, Delete
	
	// Create
	T create(T newObj);
	
	// Read
	List<T> findAll();
	T findById(String id);
	
	//Update
	boolean update(T updatedObj);
	
	//Delete
	boolean delete(String id);
}
