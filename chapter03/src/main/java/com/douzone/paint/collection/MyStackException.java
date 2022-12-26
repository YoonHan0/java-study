package com.douzone.paint.collection;

public class MyStackException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MyStackException() {
		// Prob5.MyStackException: stack is empty
		// ArrayIndexOutOfBoundsException
		super("stack is empty");
	}
}
