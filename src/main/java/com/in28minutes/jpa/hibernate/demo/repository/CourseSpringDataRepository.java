package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	public List<Course> findByName(String name);

	public List<Course> findByNameAndId(String name, Long id);

	public List<Course> countByName(String name);

	public List<Course> findByNameOrderByIdDesc(String name);

	public List<Course> deleteByName(String name);

	@Query("select c from course where name like '%50%'")
	public List<Course> courseWithNameLike50();

	@Query(value = "select * from course where name like '%50%'", nativeQuery = true)
	public List<Course> courseWithNameLike50NativeQuery();

	@Query(name = CourseRepository.FIND_ALL)
	public List<Course> allCoursesNamedQuery();
}
