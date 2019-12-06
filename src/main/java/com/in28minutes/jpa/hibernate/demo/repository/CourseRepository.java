package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class CourseRepository {
	public static final String FIND_ALL = "FIND_ALL";
	public static final String FIND_BY_NAME = "FIND_BY_NAME";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private StudentRepository studentRepo;

	public List<Course> findAll() {
		return em.createNamedQuery(FIND_ALL, Course.class).getResultList();
	}

	public Course findById(long id) {
		return em.find(Course.class, id);
	}

	public List<Course> findByName(String name) {
		return em.createNamedQuery(FIND_BY_NAME, Course.class).setParameter("name", name).getResultList();
	}

	public Course save(Course course) {
		// em.persist(course);
		// return course;
		return em.merge(course);
	}

	public Course insert(Course course) {
		em.persist(course);
		return course;
	}

	public void deleteById(long id) {
		Course course = findById(id);

		// Handle the removing of the student_course link
		for (Student student : course.getStudents()) {
			student.getCourses().remove(course);
		}

//		course.setStudents(new ArrayList<Student>());

		em.remove(course);
	}

	public void playWithEntityManager() {
//		// Transaction Start
//		Course course = new Course("My new course");
//		em.persist(course);
//		em.flush(); // Write to DB so that the created_date is populated - seems to be a bug in
//					// hibernate where the created date is made null if an insert and update is done
//					// before persisting to the DB inbetween
//		// Only 1 update is done
//		course.setName("My updated course");
//		course.setName("My updated course 2");
//
//		course = new Course("My 2nd new course");
//		em.persist(course);
//		em.flush(); // Hibernate created_date bug
//		course.setName("My updated 2nd new course");
//		em.flush(); // Results in 2 update statements
//		course.setName("My updated 2nd new course 2");
//
//		course = new Course("My 3rd new course");
//		course.setName("My updated 3rd new course");
//		em.persist(course);
//		em.flush();
//
//		em.detach(course); // This course instance is no longer tracked
//		em.clear(); // Untrack all course instances
//		course.setName("My updated 3rd new course 2");
//
//		Course course4 = new Course("My 4th new course");
//		em.persist(course4);
//		em.flush(); // Write to the DB
//
//		course4.setName("My updated 4th new course");
//		em.refresh(course4); // Get the contents from the DB
//		logger.info("Course 4: {}", course4); // "My 4th new course"

		addReviews();
		getStudents(10001);

//		// Transaction End
	}

	private void addReviews() {
		Course course = findById(10002);

		Review review = new Review("1", "Why did I bother?");
		review.setCourse(course);
		review.setStudent(studentRepo.findById(20001));
//		course.getReviews().add(review);
		em.persist(review);

		review = new Review("5", null);
		review.setCourse(course);
		review.setStudent(studentRepo.findById(20003));
//		course.getReviews().add(review);
		em.persist(review);
//		em.flush();

//		logger.info("Course 10002's Reviews: {}", course.getReviews());
	}

	public List<Review> getReviews() {
		Course course = findById(10002);
		List<Review> reviews = course.getReviews();
		logger.info("Course 10002's Reviews: {}", reviews);

		for (Review review : reviews) {
			logger.info("Review {}'s student: {}", review.getId(), review.getStudent());
		}

		return course.getReviews();
	}

	public void getStudents(long id) {
		Course course = findById(id);
		logger.info("Course {}'s Students: {}", id, course.getStudents());
	}
}
