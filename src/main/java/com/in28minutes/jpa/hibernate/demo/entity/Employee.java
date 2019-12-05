package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // Default inheritance is SINGLE_TABLE
//@DiscriminatorColumn(name = "EmployeeType") // Default is DTYPE and only used for SINGLE_TABLE
public abstract class Employee {
	@Id
	@GeneratedValue
	private long id;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	protected Employee() {
		super();
	}

	protected Employee(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
}
