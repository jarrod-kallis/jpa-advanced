package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	@PersistenceContext
	private EntityManager em;

	public Course findById(long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		return em.merge(course);
	}

	public void deleteById(long id) {
		Course course = findById(id);
		em.remove(course);
	}
}
