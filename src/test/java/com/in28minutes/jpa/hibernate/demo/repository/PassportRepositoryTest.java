package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PassportRepositoryTest extends BaseTest {

	@Autowired
	private EntityManager em;

	@Test
	// Don't need @Transactional here, because when querying the "child" passport
	// table, Hibernate automatically queries the "parent" student table as well
	public void getPassportAndStudent() {
		Passport passport = em.find(Passport.class, 30002L);
		log("Passport 30002: " + passport);
		log("Associated student: " + passport.getStudent());
	}

}
