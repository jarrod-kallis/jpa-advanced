package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

@Entity
//@Table(name = "CourseDetails") // Table will be "course_details"
@NamedQueries(value = { @NamedQuery(name = CourseRepository.FIND_ALL, query = "select c from Course c"),
		@NamedQuery(name = CourseRepository.FIND_BY_NAME, query = "select c from Course c where c.name like CONCAT('%', :name, '%')") })
public class Course {
	@Id
	@GeneratedValue
	private long id;

	// @Column(name = "ColumnName") // Column will be "course_name"
	@Column(nullable = false, unique = true)
	private String name;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	// If the relationship is Many then it is usually initialised on creation
	@OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
	private List<Review> reviews = new ArrayList<Review>();

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<Student>();

	protected Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ "]";
	}

}
