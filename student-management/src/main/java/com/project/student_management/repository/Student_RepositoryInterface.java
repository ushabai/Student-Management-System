package com.project.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.student_management.entity.StudentDo;

public interface Student_RepositoryInterface extends JpaRepository<StudentDo,Long >{

}
