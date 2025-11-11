package com.project.student_management.service;

import java.util.List;
import java.util.Map;

import com.project.student_management.dto.StudentDto;
import com.project.student_management.entity.StudentDo;

public interface Student_Interfacee {
	public String createStudent(StudentDto studentdto);
	public List<StudentDto> getAllStudentList();
	public StudentDto getStudentDto(Long studentId);
	public StudentDto convertDoToDto(StudentDo studentDo);
	public String updateStudentPutMethod(StudentDto studentDto, Long studentId);
	public String partialUpdate(Long studentId, Map<String, Object> updates);
	public String deleteStudent(Long studentId);
	public List<StudentDto> getAllStudentSortByName();
	public StudentDo[] getAllStudentSortByMarks();

}
