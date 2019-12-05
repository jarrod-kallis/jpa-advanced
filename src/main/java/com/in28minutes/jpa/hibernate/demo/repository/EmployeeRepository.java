package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Employee;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Employee> findAll() {
		return em.createQuery("select e from Employee e", Employee.class).getResultList();
	}

	// Because of MappedSuperclass
	public List<PartTimeEmployee> findAllPartTimeEmployees() {
		return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}

	// Because of MappedSuperclass
	public List<FullTimeEmployee> findAllFullTimeEmployees() {
		return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}

//	public Employee findById(long id) {
//		return em.find(Employee.class, id);
//	}

	// Because of MappedSuperclass
	public Employee findById(long id) {
		Employee employee = em.find(PartTimeEmployee.class, id);

		if (employee == null) {
			employee = em.find(FullTimeEmployee.class, id);
		}

		return employee;
	}

	public Employee save(Employee employee) {
		return em.merge(employee);
	}
}
