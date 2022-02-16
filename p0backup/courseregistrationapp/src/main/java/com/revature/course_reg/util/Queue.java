package com.revature.course_reg.util;

public interface Queue<T> extends Collection<T> {
	T poll();
    T peek();
}