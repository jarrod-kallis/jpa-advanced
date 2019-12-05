package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest extends BaseTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	@SuppressWarnings("rawtypes")
	public void findAllCoursesUntyped() {
		List courses = em.createQuery("select c from Course c").getResultList();

		log(this.getClass().getName() + "." + new Throwable().getStackTrace()[0].getMethodName(), courses);
//		logger.info("{}: {}", this.getClass().getName() + "." + new Throwable().getStackTrace()[0].getMethodName(),
//				courses);
	}

	@Test
	public void findAllCoursesTyped() {
		List<Course> courses = em.createQuery("select c from Course c", Course.class).getResultList();

		log(this.getClass().getName() + "." + new Throwable().getStackTrace()[0].getMethodName(), courses);
	}

	@Test
	public void findCourseWhere() {
		List<Course> courses = em
				.createQuery("select c from Course c where c.id = 1 or c.name like '%50%'", Course.class)
				.getResultList();

		log(this.getClass().getName() + "." + new Throwable().getStackTrace()[0].getMethodName(), courses);
	}
}
