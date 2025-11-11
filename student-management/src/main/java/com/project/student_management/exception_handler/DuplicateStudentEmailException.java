package com.project.student_management.exception_handler;

public class DuplicateStudentEmailException extends RuntimeException {
		public DuplicateStudentEmailException(String msg) {
			super(msg);
		}
}
