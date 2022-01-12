package com.nosql.exception;

public class TodoException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6951185717868181119L;

	public TodoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public TodoException(Throwable throwable, String message) {
		new Throwable(message, throwable);
	}

}
