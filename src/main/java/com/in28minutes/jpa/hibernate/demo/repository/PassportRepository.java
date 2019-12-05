package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;

@Repository
@Transactional
public class PassportRepository {

	@PersistenceContext
	private EntityManager em;

	public Passport save(Passport passport) {
		return em.merge(passport);
	}

}
