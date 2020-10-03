package com.sporty.shoes.exceptionHandler;

public class MyAuthException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public MyAuthException() {
		super();
	}

	public MyAuthException(String message) {
		super(message);
	}

}
