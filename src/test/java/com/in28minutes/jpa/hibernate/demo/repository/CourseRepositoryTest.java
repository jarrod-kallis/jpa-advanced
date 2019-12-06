package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

// Launches tests with a Spring Context
@RunWith(SpringRunner.class)
@SpringBootTest // (classes = DemoApplication.class) <-- Still trying to figure
				// out what this does
public class CourseRepositoryTest {

	// private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepo;

	@Test
	public void findAll() {
		List<Course> courses = courseRepo.findAll();
		assertEquals(4, courses.size());
	}

	@Test
	public void findByName() {
		List<Course> courses = courseRepo.findByName("50");
		assertEquals(1, courses.size());
	}

	@Test
	public void findById() {
		Course course = courseRepo.findById(10001);
		assertEquals("JPA in 50 steps", course.getName());
	}

	@Test
	// Indicate to Spring that this test changes the data.
	// Spring would then reinstate the data as it was before the test was run.
	@DirtiesContext
	public void deleteById() {
		courseRepo.deleteById(10002);
		Course course = courseRepo.findById(10002);
		assertNull(course);
	}

	@Test
	@DirtiesContext
	public void insert() {
		Course course = courseRepo.save(new Course("New course"));
		assertNotEquals(0, course.getId());
	}

	@Test
	@DirtiesContext
	public void update() {
		Course course = courseRepo.findById(10001);
		course.setName("Updated course");
		course = courseRepo.save(course);
		assertEquals("Updated course", courseRepo.findById(10001).getName());
	}
}
