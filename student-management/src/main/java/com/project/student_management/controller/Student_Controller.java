package com.project.student_management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.student_management.dto.StudentDto;
import com.project.student_management.entity.StudentDo;
import com.project.student_management.service_implementation.Student_Service;

@RestController
@RequestMapping("/student")
public class Student_Controller {
	@Autowired
	private Student_Service service;
	
	@PostMapping("/createStudent")
	public String createStudent(@RequestBody StudentDto studentDto) {
		return service.createStudent(studentDto);
		
	}
	@GetMapping("/getAllStudent")
	public List<StudentDto> getAllStudent(){
		return service.getAllStudentList();
	}
	@GetMapping("/getStudent/{studentId}")
	public StudentDto getStudent(@PathVariable Long studentId) {
		return service.getStudentDto(studentId);
		
	}
	@PutMapping("/updateStudent/{studentId}")
	public String updateStudent(@RequestBody StudentDto studentDto,@PathVariable Long studentId)
	{
		return service.updateStudentPutMethod(studentDto, studentId);
	}
	@PatchMapping("/partialUpdate/{studentId}")
	public String partialStudentUpdate(@PathVariable Long studentId, @RequestBody Map<String, Object> updates) {
		return service.partialUpdate(studentId,updates);
	}
	@GetMapping("/delStudent/{studentId}")
	public String delStudent(@PathVariable Long studentId) {
		return service.deleteStudent(studentId);
	}
	@GetMapping("/getAllStudentByName")
	public List<StudentDto> getAllStudentByName(){
		return service.getAllStudentSortByName();
	}
	@GetMapping("/getAllStudentByMarks")
	public StudentDo[] getAllStudentByMarks(){
		return service.getAllStudentSortByMarks();
	}
	
	
	
}
