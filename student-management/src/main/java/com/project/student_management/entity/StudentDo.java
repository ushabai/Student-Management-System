package com.project.student_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
public class StudentDo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long studentId;
	
	@Column
	private String studentName;
	
	@Column
	private int age;
	
	@Column(unique = true)
	private String studentEmail;
	
	@Column
	private String grade;
	
	@Column
	private int marks;

	public StudentDo() {
		
	}

	public StudentDo(Long studentId, String studentName, int age, String studentEmail, String grade, int marks) {
		
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
