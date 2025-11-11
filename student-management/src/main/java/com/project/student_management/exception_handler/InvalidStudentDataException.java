package com.project.student_management.exception_handler;

public class InvalidStudentDataException extends RuntimeException{
	public InvalidStudentDataException(String msg) {
		super(msg);
	}

}
