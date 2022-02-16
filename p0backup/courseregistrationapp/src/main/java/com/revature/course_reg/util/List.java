package com.revature.course_reg.util;

public interface List<T> extends Collection<T> {
	
	T get(int index);
    void add(int index, T element);
    T set(int index, T element);
    T remove(int index);
    int indexOf(T element);
    int lastIndexOf(T element);
}