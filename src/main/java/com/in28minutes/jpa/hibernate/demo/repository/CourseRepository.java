package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager em;

	public Course findById(long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		// em.persist(course);
		// return course;
		return em.merge(course);
	}

	public void deleteById(long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		// Transaction Start
		Course course = new Course("My new course");
		em.persist(course);
		// Only 1 update is done
		course.setName("My updated course");
		course.setName("My updated course 2");

		course = new Course("My 2nd new course");
		em.persist(course);
		course.setName("My updated 2nd new course");
		em.flush(); // Results in 2 update statements
		course.setName("My updated 2nd new course 2");

		course = new Course("My 3rd new course");
		course.setName("My updated 3rd new course");
		em.persist(course);
		em.flush();

		em.detach(course); // This course instance is no longer tracked
		em.clear(); // Untrack all course instances
		course.setName("My updated 3rd new course 2");

		Course course4 = new Course("My 4th new course");
		em.persist(course4);
		em.flush(); // Write to the DB

		course4.setName("My updated 4th new course");
		em.refresh(course4); // Get the contents from the DB
		logger.info("Course 4: {}", course4); // "My 4th new course"
		// Transaction End
	}
}
