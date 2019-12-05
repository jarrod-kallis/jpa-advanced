package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager em;

	@Autowired
	@Lazy // There is a circular reference
	private CourseRepository courseRepo;

	public Student findById(long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		return em.merge(student);
	}

	public void deleteById(long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void deletePassportById(long id) {
		Passport passport = em.find(Passport.class, id);
		em.remove(passport);
	}

	public void playWithEntityManager() {
//		Passport passport = new Passport("JMK123456");
//		passport.setNumber("TSW123456");
//		em.persist(passport);
//
//		Student student = new Student("Michael", "Kallis");
//		student.setPassport(passport);
//		em.persist(student);

		getCourses(20003);
		addCourse(10002, 20003);
		getCourses(20003);
	}

	private void getCourses(long id) {
		Student student = findById(id);
		logger.info("Student {}'s courses: {}", id, student.getCourses());
	}

	private void addCourse(long courseId, long studentId) {
		Student student = findById(studentId);

		Course course = em.find(Course.class, courseId);
		student.getCourses().add(course);
		getCourses(20003);
	}

	public void createStudentAndCourseAndLink() {
		Course course1 = courseRepo.save(new Course("Impi Challenge"));
		Course course2 = courseRepo.save(new Course("Bay To Bay Challenge"));

		Student student = new Student("Ryan", "Johnson");
		student.getCourses().add(course1);
		student.getCourses().add(course2);

		save(student);
	}
}
