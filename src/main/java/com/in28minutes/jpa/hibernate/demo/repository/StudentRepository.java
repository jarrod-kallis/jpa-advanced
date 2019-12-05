package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
public class StudentRepository {
	@PersistenceContext
	private EntityManager em;

	public Student findById(long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		return em.merge(student);
	}

	public void playWithEntityManager() {
//		Passport passport = new Passport("JMK123456");
//		em.persist(passport);
//
//		Student student = new Student("Michael", "Kallis");
//		student.setPassport(passport);
//		em.persist(student);
	}
}
