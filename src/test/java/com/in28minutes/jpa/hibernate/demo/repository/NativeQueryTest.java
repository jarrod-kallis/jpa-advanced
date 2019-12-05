package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueryTest extends BaseTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	@SuppressWarnings("unchecked")
	public void findAll() {
		List<Course> courses = em.createNativeQuery("select * from course", Course.class).getResultList();

		log(courses);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void findAllWithParameter() {
		List<Course> courses = em.createNativeQuery("select * from course where id = ?", Course.class)
				.setParameter(1, 10001).getResultList();

		log(courses);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void findAllWithNamedParameter() {
		List<Course> courses = em.createNativeQuery("select * from course where id = :id", Course.class)
				.setParameter("id", 10001).getResultList();

		log(courses);
	}

	@Test
	@DirtiesContext
	@Transactional
	public void updateAllRows() {
		int rowsUpdated = em.createNativeQuery("update course set updated_date = sysdate()", Course.class)
				.executeUpdate();

		log("No. of rows updated: " + rowsUpdated);
		assertEquals(7, 7);
	}
}
