package com.project.student_management.dto;

public class StudentDto {
	private Long studentId;
	private String studentName;
	private int age;
	private String studentEmail;
	private String grade;
	private int marks;
	
	public StudentDto() {
		
	}
	
	public StudentDto(Long studentId, String studentName, int age, String studentEmail, String grade, int marks) {
		
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
		this.studentEmail = studentEmail;
		this.grade = grade;
		this.marks = marks;
	}

	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", studentName=" + studentName + ", age=" + age
				+ ", studentEmail=" + studentEmail + ", grade=" + grade + ", marks=" + marks + "]";
	}
	

}
