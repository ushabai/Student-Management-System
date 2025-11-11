Student Management System– Spring Boot + JUnit + Mockito
Project Overview

The Student Management System is a Spring Boot–based application that manages student records efficiently.
It provides full CRUD (Create, Read, Update, Delete) functionality and includes additional features such as:
- automatic grade assignment based on marks, and
- sorting of student records by name and marks.
The project also includes comprehensive JUnit and Mockito test cases to ensure correctness and reliability of all service methods.

Key Features

✅ Create Student – Add a new student record with name, email, age, and marks.
- Automatically assigns a grade based on marks:
- A → Marks ≥ 90
- B → Marks ≥ 75
- C → Marks ≥ 50
- F → Marks < 50
  
✅ Read Students – Fetch all students or a specific student by ID.

✅ Update Student (PUT) – Fully update all student details using the student ID.

✅ Partial Update (PATCH) – Update only selected fields (like name, email, age, or marks).
- Automatically recalculates the grade when marks are updated.
  
✅ Delete Student – Remove a student record safely with exception handling for missing IDs.

✅ Sorting Functionality
- Sort students by name using a Comparator.
- Sort students by marks using Arrays.sort() in descending order.
  
✅ Exception Handling
- StudentNotFoundException – when ID is invalid or not found
- DuplicateStudentEmailException – when email already exists
- InvalidStudentDataException – when an invalid field is provided during partial update
  
✅ Unit Testing
- Each method is covered using JUnit 5 and Mockito.
- Repository is mocked to isolate service layer logic.
- Validates both success and failure scenarios.

Technologies Used
- Backend Framework :	Spring Boot
- Language : Java
- Testing Frameworks : JUnit 5, Mockito
- Database Layer	: Spring Data JPA (Repository Interface)
- Build Tool	: Maven
- IDE	: IntelliJ IDEA / Eclipse

Sample Test Cases


- createStudentTest() :	Verifies successful creation of a student
- createStudentDuplicateEmailTest()	: Checks handling of duplicate emails
- getStudentDto_InvalidException()	: Validates exception for invalid student ID
- updateStudent_WhenStudentIdNotFound()	: Ensures error when updating non-existent student
- partialUpdateTest_WhenIdFound()	: Checks partial update logic and grade recalculation
- getAllStudentSortByNameTest()	: Tests sorting of students by name
- getAllStudentSortByMarksTest() :	Tests sorting of students by marks

Advantages
- Layered architecture (Service → Repository → Entity → DTO).
- Automated testing ensures higher code quality and maintainability.
- Reusable design – can be extended to include more features like pagination, filtering, or REST APIs.
- Error resilience – custom exceptions make debugging easier.
- Performance friendly – sorting and grade computation are simple and efficient.
