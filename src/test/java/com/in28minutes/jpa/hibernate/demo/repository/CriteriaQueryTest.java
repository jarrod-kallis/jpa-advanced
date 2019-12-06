package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaQueryTest extends BaseTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void findAllCourses() {
		// Use Criteria Builder to create a Query
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		// Course.class indicates the class type in the result
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

		// Indicate which tables are involved in the query
		Root<Course> courseRoot = createQuery.from(Course.class);

		// Build the query
		List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

		log("Criteria Query - All Courses: " + courses);
	}

	@Test
	public void findAllCoursesWithNameLike50() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

		Root<Course> courseRoot = createQuery.from(Course.class);

		Predicate nameLike50 = criteriaBuilder.like(courseRoot.get("name"), "%50%");
		createQuery.where(nameLike50);

		List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

		log("Criteria Query - All Courses where name like '%50%': " + courses);
	}

	@Test
	public void findAllCoursesWithNoStudents() {
		// "select c from Course c where c.students is empty"

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

		Root<Course> courseRoot = createQuery.from(Course.class);

		Predicate noStudents = criteriaBuilder.isEmpty(courseRoot.get("students"));
		createQuery.where(noStudents);

		List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

		log("Criteria Query - All Courses with no students: " + courses);
	}

	@Test
	public void findAllCoursesAndTheirStudents() {
		// "select c, s from Course c JOIN c.students s"

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

		Root<Course> courseRoot = createQuery.from(Course.class);
		Join<Object, Object> join = courseRoot.join("students");

		List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

		log("Criteria Query - All Courses and students: " + courses);
	}

	@Test
	public void findAllCoursesEvenWithNoStudents() {
		// "select c, s from Course c LEFT JOIN c.students s"

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

		Root<Course> courseRoot = createQuery.from(Course.class);
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

		log("Criteria Query - All Courses and students, even with no students: " + courses);
	}
}
