package com.hibernate.crud.advanceMappingOneToMany.DAO;

import java.util.List;

import com.hibernate.crud.advanceMappingOneToMany.entity.Course;
import com.hibernate.crud.advanceMappingOneToMany.entity.Instructor;
import com.hibernate.crud.advanceMappingOneToMany.entity.InstructorDetail;

public interface DAO {

	public void save(Instructor instructor);
	
	public Instructor findInstructorById(int id);
	
	public void deleteInstructorById(int id);
	
	public InstructorDetail findInstructorDetailById(int id);
	
	public void deleteInstructorDetailById(int id);
	
	public List<Course> findCoursesByInstructorId(int id);
	
	public Instructor findInstructorWithCoursesJoinFetch(int id);
	
	public void update(Instructor tempInstructor);
	
	public void updateCourse(Course tempCourse);
	
	public Course findCourseById(int id);
	
	public void deleteCourseById(int id);
	
	public void saveCourse(Course course);
	
	public Course findCourseAndReviewByCourseId(int id);
	
	
}
