package com.in28minutes.jpa.hibernate.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// logger.info("Course 10001: {}", courseRepo.findById(10001));

		// Course course = new Course("New course 1");
		// course = courseRepo.save(course);
		// logger.info("Create new course: {}", course);
		//
		// course.setName("Not so new course 1");
		// logger.info("Update new course: {}", courseRepo.save(course));

		// logger.info("Delete course 10003");
		// courseRepo.deleteById(10003);

		courseRepo.playWithEntityManager();
	}

}
