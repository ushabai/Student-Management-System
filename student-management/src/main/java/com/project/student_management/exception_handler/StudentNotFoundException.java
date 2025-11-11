package com.project.student_management.exception_handler;

public class StudentNotFoundException extends RuntimeException{
	public StudentNotFoundException(String msg) {
		super(msg);
	}

}
