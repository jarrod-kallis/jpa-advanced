package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
// @Transactional // Keeps transactions alive for all methods
public class StudentRepositoryTest extends BaseTest {

	@Autowired
	private StudentRepository repo;

	@Test
	@Transactional // Keeps transaction alive till the end of this method
	public void getStudentAndPassport() {
		Student student = repo.findById(20001);

		log(student);
		log(student.getPassport());
	}

	@Test
	@Transactional
	public void getCourses() {
		Student student = repo.findById(20001);
		assertEquals(3, student.getCourses().size());
	}
}
