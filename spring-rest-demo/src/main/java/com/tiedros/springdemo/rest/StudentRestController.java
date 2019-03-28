package com.tiedros.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiedros.springdemo.entity.Student;
import com.tiedros.springdemo.exception.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;
	
	// initialize student. this happens once after spring context loads
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		theStudents.add(new Student("Tiedros", "Abrha"));
		theStudents.add(new Student("Mary", "Tom"));
		theStudents.add(new Student("John", "Chris"));
		
		
	}
	
	// add endpoint for /student that returns list of students
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		
		return theStudents;
	}
	
	// add endpoint for /student/{studentId} that returns student at index of studentId
	@GetMapping("/student/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		// just index into the list. Just keep it simple for now.
		
		// check the student id agains the list size
		if((studentId  >= theStudents.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		return theStudents.get(studentId);
	}
	
	
}















