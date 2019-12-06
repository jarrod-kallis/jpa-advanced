package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest extends BaseTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	@SuppressWarnings("rawtypes")
	public void findAllCoursesUntyped() {
		List courses = em.createQuery("select c from Course c").getResultList();

		log(courses);
		// logger.info("{}: {}", this.getClass().getName() + "." + new
		// Throwable().getStackTrace()[0].getMethodName(),
		// courses);
	}

	@Test
	public void findAllCoursesTyped() {
		List<Course> courses = em.createQuery("select c from Course c", Course.class).getResultList();

		log(courses);
	}

	@Test
	public void findCourseWhere() {
		List<Course> courses = em
				.createQuery("select c from Course c where c.id = 1 or c.name like '%50%'", Course.class)
				.getResultList();

		log(courses);
	}

	@Test
	public void findCoursesWithNoStudents() {
		List<Course> courses = em.createQuery("select c from Course c where c.students is empty", Course.class)
				.getResultList();

		log("Courses with no students: " + courses);
		assertEquals(1, courses.size());
	}

	@Test
	public void findCoursesWithMoreThan2Students() {
		List<Course> courses = em.createQuery("select c from Course c where size(c.students) > 2", Course.class)
				.getResultList();

		log("Courses with more than 2 students: " + courses);
		assertEquals(1, courses.size());
	}

	@Test
	public void orderCoursesByNumberOfStudents() {
		List<Course> courses = em.createQuery("select c from Course c order by size(c.students) desc", Course.class)
				.getResultList();

		log("Order Courses by students: " + courses);
	}

	@Test
	public void findStudentsWherePassportsLike123() {
		List<Student> students = em
				.createQuery("select s from Student s where s.passport.number like '%123%'", Student.class)
				.getResultList();

		log("Students where passport like '%123%': " + students);
		assertEquals(2, students.size());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void findCoursesAndTheirStudents() {
		List<Object[]> coursesAndStudents = em.createQuery("select c, s from Course c JOIN c.students s")
				.getResultList();

		log("Courses & Students Result Size: " + coursesAndStudents.size());

		for (Object[] result : coursesAndStudents) {
			log("Course " + result[0] + ", Student " + result[1]);
		}

		assertEquals(6, coursesAndStudents.size());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void findAllCoursesAndTheirStudents() {
		List<Object[]> coursesAndStudents = em.createQuery("select c, s from Course c LEFT JOIN c.students s")
				.getResultList();

		log("Courses & Students Result Size: " + coursesAndStudents.size());

		for (Object[] result : coursesAndStudents) {
			log("Course " + result[0] + ", Student " + result[1]);
		}

		assertEquals(7, coursesAndStudents.size());
	}

	// Cartesian product
	@Test
	@SuppressWarnings("unchecked")
	public void findAllCoursesAndAllStudents() {
		List<Object[]> coursesAndStudents = em.createQuery("select c, s from Course c, Student s").getResultList();

		log("Courses & Students Result Size: " + coursesAndStudents.size());

		for (Object[] result : coursesAndStudents) {
			log("Course " + result[0] + ", Student " + result[1]);
		}

		assertEquals(12, coursesAndStudents.size());
	}
}
