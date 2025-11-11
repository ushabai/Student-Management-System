package com.project.student_management.exception_handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex){
		return ResponseEntity.badRequest().body("Error : "+ex.getMessage());
	}
	@ExceptionHandler(InvalidStudentDataException.class)
	public ResponseEntity<String> handleInvalidData(InvalidStudentDataException ex){
		return ResponseEntity.badRequest().body("Error : "+ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex){
		return ResponseEntity.badRequest().body("Error : "+ex.getMessage());
	}

}
