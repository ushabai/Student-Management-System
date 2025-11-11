package com.project.student_management.service_implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.student_management.dto.StudentDto;
import com.project.student_management.entity.StudentDo;
import com.project.student_management.exception_handler.DuplicateStudentEmailException;
import com.project.student_management.exception_handler.InvalidStudentDataException;
import com.project.student_management.exception_handler.StudentNotFoundException;
import com.project.student_management.repository.Student_RepositoryInterface;
import com.project.student_management.service.Student_Interfacee;

@Service
public class Student_Service implements Student_Interfacee {
	@Autowired
	private Student_RepositoryInterface repo;

	public String createStudent(StudentDto studentdto) {
		try {
			StudentDo studentDo = new StudentDo();

			studentDo.setStudentName(studentdto.getStudentName());
			studentDo.setAge(studentdto.getAge());
			studentDo.setStudentEmail(studentdto.getStudentEmail());
			studentDo.setMarks(studentdto.getMarks());
			String grade = (studentdto.getMarks() >= 90) ? "A"
					: (studentdto.getMarks() >= 75) ? "B" : (studentdto.getMarks() >= 50) ? "C" : "F";
			studentDo.setGrade(grade);
			repo.save(studentDo);
			return "Student created successfully";
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateStudentEmailException(
					"Student email must be unique. The email " + studentdto.getStudentEmail() + " is already in use.");
		}
	}

	public List<StudentDto> getAllStudentList() {
		List<StudentDo> studentDoList = repo.findAll();
		List<StudentDto> studentDtoList = new ArrayList<>();
		for (StudentDo temp : studentDoList) {
			StudentDto studentDto1 = new StudentDto();
			studentDto1.setStudentId(temp.getStudentId());
			studentDto1.setStudentName(temp.getStudentName());
			studentDto1.setAge(temp.getAge());
			studentDto1.setStudentEmail(temp.getStudentEmail());
			studentDto1.setMarks(temp.getMarks());
			studentDto1.setGrade(temp.getGrade());
			studentDtoList.add(studentDto1);

		}
		return studentDtoList;

	}

	public StudentDto getStudentDto(Long studentId) {
		if (studentId < 1) {
			throw new StudentNotFoundException("Student Id should be greater than 1");
		}
		Optional<StudentDo> student = repo.findById(studentId);
		if (student.isEmpty()) {
			throw new StudentNotFoundException("Student not found with ID: " + studentId);

		}
		return convertDoToDto(student.get());

	}

	public StudentDto convertDoToDto(StudentDo studentDo) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(studentDo.getStudentId());
		studentDto.setStudentName(studentDo.getStudentName());
		studentDto.setAge(studentDo.getAge());
		studentDto.setStudentEmail(studentDo.getStudentEmail());
		studentDto.setMarks(studentDo.getMarks());
		studentDto.setGrade(studentDo.getGrade());
		return studentDto;

	}

	public String updateStudentPutMethod(StudentDto studentDto, Long studentId) {
		// TODO Auto-generated method stub
		Optional<StudentDo> studentDo = repo.findById(studentId);
		if (studentDo.isEmpty()) {
			throw new StudentNotFoundException("Student not found with ID: " + studentId);

		}
		StudentDo studentDoo = studentDo.get();

		studentDoo.setStudentName(studentDto.getStudentName());
		studentDoo.setAge(studentDto.getAge());
		studentDoo.setStudentEmail(studentDto.getStudentEmail());
		studentDoo.setMarks(studentDto.getMarks());
		int marks = studentDto.getMarks();
		String grade = (marks >= 90) ? "A" : 
					   (marks >= 75) ? "B" : 
					   (marks >= 50) ? "C" : "F";
		studentDoo.setGrade(grade);
		repo.save(studentDoo);
		return "Student fully updated successfully";
	}

	public String partialUpdate(Long studentId, Map<String, Object> updates) {
		// TODO Auto-generated method stub
		Optional<StudentDo> studentDo = repo.findById(studentId);
		if (studentDo.isEmpty()) {
			throw new StudentNotFoundException("Student not found with ID: " + studentId);

		}
		StudentDo studentDooo = studentDo.get();
		updates.forEach((key, value) -> {
			switch (key) {
			case "studentName":
				studentDooo.setStudentName((String) value);
				break;
			case "studentEmail":
				studentDooo.setStudentEmail((String) value);
				break;
			case "age":
				studentDooo.setAge((Integer) value);
				break;
			case "marks":
				int marks = (Integer) value;
				studentDooo.setMarks(marks);

	                
				
	                String grade = (marks >= 90) ? "A" :
	                               (marks >= 75) ? "B" :
	                               (marks >= 50) ? "C" : "F";
	                studentDooo.setGrade(grade);
				break;
			default:
				throw new InvalidStudentDataException("Invalid field: " + key);

			}
		});

		repo.save(studentDooo);
		return "Student partially updated successfully";
	}

	public String deleteStudent(Long studentId) {
		Optional<StudentDo> studentDo = repo.findById(studentId);
		if (studentDo.isEmpty()) {
			throw new StudentNotFoundException("Student not found with ID: " + studentId);

		}
		repo.deleteById(studentId);
		return "Student deleted successfully";
	}

	public List<StudentDto> getAllStudentSortByName() {
		List<StudentDo> studentDoListSortByName = repo.findAll();
		List<StudentDto> studentDtoListSortByName = new ArrayList<>();
		for (StudentDo temp : studentDoListSortByName) {
			StudentDto studentDtoName = new StudentDto();
			studentDtoName.setStudentId(temp.getStudentId());
			studentDtoName.setStudentName(temp.getStudentName());
			studentDtoName.setStudentEmail(temp.getStudentEmail());
			studentDtoName.setAge(temp.getAge());
			studentDtoName.setMarks(temp.getMarks());
			studentDtoName.setGrade(temp.getGrade());
			studentDtoListSortByName.add(studentDtoName);

		}
		Collections.sort(studentDtoListSortByName, new Comparator<StudentDto>() {

			@Override
			public int compare(StudentDto o1, StudentDto o2) {
				return o1.getStudentName().compareTo(o2.getStudentName());

			}

		});
		return studentDtoListSortByName;

	}

	public StudentDo[] getAllStudentSortByMarks() {
		List<StudentDo> studentList = repo.findAll();
		StudentDo[] studentArray = studentList.toArray(new StudentDo[0]);
		Arrays.sort(studentArray, (s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks()));
		return studentArray;

	}

}
