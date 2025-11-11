package com.project.student_management.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.project.student_management.dto.StudentDto;
import com.project.student_management.entity.StudentDo;
import com.project.student_management.exception_handler.DuplicateStudentEmailException;
import com.project.student_management.exception_handler.InvalidStudentDataException;
import com.project.student_management.exception_handler.StudentNotFoundException;
import com.project.student_management.repository.Student_RepositoryInterface;
import com.project.student_management.service_implementation.Student_Service;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	@Mock
	private Student_RepositoryInterface repo;
	@InjectMocks
	private Student_Service service;
	
	
	@Test
	public void createStudentTest() {
		StudentDto  dto=new StudentDto();
		dto.setStudentName("Roshan");
		dto.setAge(11);
		dto.setStudentEmail("roshan@example.com");
		dto.setMarks(92);
		StudentDo doobj=new StudentDo();
		doobj.setStudentName(dto.getStudentName());
		doobj.setAge(dto.getAge());
		doobj.setStudentEmail(dto.getStudentEmail());
		doobj.setMarks(dto.getMarks());
		doobj.setGrade("A");
		when(repo.save(any(StudentDo.class))).thenReturn(doobj);
		String result=service.createStudent(dto);
		assertEquals("Student created successfully", result);
		verify(repo,times(1)).save(any(StudentDo.class));
		
		
	}
	@Test
	public void createStudentDuplicateEmailTest() {
		StudentDto dto=new StudentDto();
		dto.setStudentName("Harsha");
		dto.setAge(15);
		dto.setStudentEmail("harsha@example.com");
		dto.setMarks(85);
		when(repo.save(any(StudentDo.class))).thenThrow(DataIntegrityViolationException.class);
		DuplicateStudentEmailException exception=assertThrows(DuplicateStudentEmailException.class, ()->service.createStudent(dto));
		assertTrue(exception.getMessage().contains("harsha@example.com"));
		verify(repo,times(1)).save(any(StudentDo.class));
	}
	@Test
	public void getAllStudentListTest() {
		List<StudentDo>studentDo=Arrays.asList(new StudentDo(1L, "Usha", 12, "usha@example.com", "A", 95));
		when(repo.findAll()).thenReturn(studentDo);
		List<StudentDto> result=service.getAllStudentList();
		assertEquals(1, result.size());
		assertEquals("Usha", result.get(0).getStudentName());
		assertEquals(12, result.get(0).getAge());
		assertEquals("usha@example.com", result.get(0).getStudentEmail());
		assertEquals("A", result.get(0).getGrade());
		verify(repo,times(1)).findAll();
	
		
				
	}
	@Test
	public void getStudentDto_InvalidException() {
		
		Long studentId=0L;
		StudentNotFoundException exception=assertThrows(StudentNotFoundException.class,()->service.getStudentDto(studentId));
		assertEquals("Student Id should be greater than 1", exception.getMessage());
		verify(repo, never()).findById(anyLong());
				
	}
	@Test
	public void getStudentDto_StudentIdNotFound() {
		Long studentId=10L;
		when(repo.findById(studentId)).thenReturn(Optional.empty());
		StudentNotFoundException exception=assertThrows(StudentNotFoundException.class, ()->service.getStudentDto(studentId));
		assertEquals("Student not found with ID: 10L",exception.getMessage() );
		verify(repo,times(1)).findById(studentId);
		
	}
	@Test
	public void getStudentDto_WhenStudentDataIsPresent() {
		Long studentId=1L;
		StudentDo studentDo=new StudentDo();
		studentDo.setStudentId(studentId);
		studentDo.setStudentName("Harsha");
		studentDo.setAge(15);
		studentDo.setStudentEmail("harsha@example.com");
		studentDo.setMarks(95);
		studentDo.setGrade("A");
		when(repo.findById(studentId)).thenReturn(Optional.of(studentDo));
		StudentDto studentDto=service.getStudentDto(studentId);
		assertEquals(studentId, studentDto.getStudentId());
		assertEquals("Harsha", studentDto.getStudentName());
		assertEquals(15, studentDto.getAge());
		assertEquals("harsha@example.com", studentDto.getStudentEmail());
		assertEquals(95, studentDto.getMarks());
		assertEquals("A", studentDto.getGrade());
		verify(repo,times(1)).findById(studentId);
		
	}
	@Test
	public void deleteStudent() {
		Long studentDo=10L;
		when(repo.findById(studentDo)).thenReturn(Optional.empty());
		StudentNotFoundException exception=assertThrows(StudentNotFoundException.class,()->service.deleteStudent(studentDo));
		assertTrue(exception.getMessage().contains("Student not found with ID"));
		verify(repo,times(1)).findById(studentDo);
		verify(repo,never()).deleteById(anyLong());
	}
	@Test
	public void deleteStudent_WhenIdPresent() {
		Long studentId=1L;
		StudentDo studentDo=new StudentDo();
		studentDo.setStudentId(studentId);
		when(repo.findById(studentId)).thenReturn(Optional.of(studentDo));
		String result=service.deleteStudent(studentId);
		assertEquals("Student deleted successfully", result);
		verify(repo,times(1)).findById(studentId);
		verify(repo,times(1)).deleteById(studentId);
		
		
	}
	@Test
	public void updateStudent_WhenStudentIdNotFound() {
		Long studentId=10L;
		StudentDto studentDto=new StudentDto();
		when(repo.findById(studentId)).thenReturn(Optional.empty());
		StudentNotFoundException exception=assertThrows(StudentNotFoundException.class, ()->service.updateStudentPutMethod(studentDto, studentId));
		assertTrue(exception.getMessage().contains("Student not found with ID: "));
		verify(repo,times(1)).findById(studentId);
		verify(repo,never()).save(any(StudentDo.class));
	}
	@Test
	public void updateStudent_WhenStudentIdPresent() {
		Long studentId=1L;
		StudentDo studentDo=new StudentDo();
		studentDo.setStudentName("Usha");
		studentDo.setAge(15);
		studentDo.setStudentEmail("usha@example.com");
		studentDo.setMarks(95);
		studentDo.setGrade("A");
		StudentDto studentDto=new StudentDto();
		studentDto.setStudentName("Usha");
		studentDto.setStudentEmail("usha@gmail.com");
		studentDto.setAge(15);
		studentDto.setMarks(95);
		studentDto.setGrade("A");
		when(repo.findById(1L)).thenReturn(Optional.of(studentDo));
		String result=service.updateStudentPutMethod(studentDto, studentId);
		assertEquals("Student fully updated successfully", result);
		verify(repo,times(1)).findById(studentId);
		verify(repo,times(1)).save(studentDo);
		
		
	}
	@Test
	public void partialUpdate_WhenStudentIdNotFound() {
		Long studentId=1L;
		when(repo.findById(studentId)).thenReturn(Optional.empty());
		StudentNotFoundException exception=assertThrows(StudentNotFoundException.class, ()->service.partialUpdate(studentId, null));
		assertTrue(exception.getMessage().contains("Student not found with ID: "));
		verify(repo,times(1)).findById(studentId);
		
	}
	@Test
	public void partialUpdateTest_WhenIdFound() {
		Long id=1L;
		StudentDo studentDo=new StudentDo();
		studentDo.setStudentId(id);
		studentDo.setStudentName("Old Name");
		studentDo.setMarks(60);
		studentDo.setGrade("C");
		when(repo.findById(id)).thenReturn(Optional.of(studentDo));
		when(repo.save(any(StudentDo.class))).thenReturn(studentDo);
		Map<String, Object> updates= new HashMap<>();
		updates.put("studentName", "New Name");
		updates.put("marks", 92);
		String result=service.partialUpdate(id, updates);
		assertEquals("Student partially updated successfully", result);
		assertEquals("New Name", studentDo.getStudentName());
		assertEquals(92, studentDo.getMarks());
		assertEquals("A", studentDo.getGrade());
		verify(repo).save(studentDo);
		
	}
	@Test
	public void partialUpdateTestWhenInvalidFieldEntered() {
		Long Id=1L;
		StudentDo studentDo=new StudentDo();
		studentDo.setStudentId(Id);
		when(repo.findById(Id)).thenReturn(Optional.of(studentDo));
		Map<String, Object> updates=new HashMap<>();
		updates.put("Invalid Field", "Some value");
		assertThrows(InvalidStudentDataException.class, ()->service.partialUpdate(Id, updates));
	}
	@Test
	public void getAllStudentSortByNameTest() {
		List<StudentDo>studentDo=Arrays.asList(new StudentDo(1L, "Usha", 12, "usha@example.com", "A", 95),new StudentDo(2L, "Girija", 21, "girija@example.com", "B	",	 80));
		when(repo.findAll()).thenReturn(studentDo);
		List<StudentDto> studentDto=service.getAllStudentSortByName();
		assertEquals(2, studentDto.size());
		assertEquals("Girija", studentDto.get(0).getStudentName());
		assertEquals("Usha", studentDto.get(1).getStudentName());
		verify(repo,times(1)).findAll();
		
	}
	@Test
	public void getAllStudentSortByName_EmptyList() {
		
		when(repo.findAll()).thenReturn(Collections.emptyList());
		List<StudentDto> result=service.getAllStudentSortByName();
		assertTrue(result.isEmpty());
		verify(repo,times(1)).findAll();
	}
	@Test
	public void getAllStudentSortByMarksTest() {
		when(repo.findAll()).thenReturn(Arrays.asList(new StudentDo(1L, "Usha", 30, "usha@example.com", "A", 90)));
		StudentDo[] result=service.getAllStudentSortByMarks();
		assertEquals(1, result.length);
		assertEquals("Usha", result[0].getStudentName());
		verify(repo,times(1)).findAll();
	}
	@Test
	public void getAllStudentSortByMarksTest_WhenArrayIsEmpty() {
		when(repo.findAll()).thenReturn(Collections.emptyList());
		StudentDo[] result=service.getAllStudentSortByMarks();
		assertEquals(0, result.length);
		verify(repo,times(1)).findAll();
	}

}
