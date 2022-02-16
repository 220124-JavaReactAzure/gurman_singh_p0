package com.revature.course_reg.util;

public interface Deque<T> extends Queue<T>{
	void addFirst(T element);
    void addLast(T element);
    T pollFirst();
    T pollLast();
    T peekFirst();
    T peekLast();
}