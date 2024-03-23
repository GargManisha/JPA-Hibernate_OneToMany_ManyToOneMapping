package com.hibernate.crud.advanceMappingOneToMany.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.crud.advanceMappingOneToMany.entity.Course;
import com.hibernate.crud.advanceMappingOneToMany.entity.Instructor;
import com.hibernate.crud.advanceMappingOneToMany.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class DAOImpl implements DAO {

	private EntityManager entityManager;
	
	@Autowired
	public DAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public void save(Instructor instructor) {
		entityManager.persist(instructor);
	}

	@Override
	public Instructor findInstructorById(int id) {
		return entityManager.find(Instructor.class, id);
	}

	@Override
	public void deleteInstructorById(int id) {
		
		// find record by id
		Instructor tempInstructor = entityManager.find(Instructor.class, id);
		
		//get courses and break association of all courses for the instructor.
		List<Course> courses = tempInstructor.getCourses();
		for(Course course: courses) {
			course.setInstructor(null);
		}
		
		//delete record by id
		entityManager.remove(tempInstructor);
	}

	@Override
	public InstructorDetail findInstructorDetailById(int id) {

		return entityManager.find(InstructorDetail.class, id);
	}

	@Override
	public void deleteInstructorDetailById(int id) {

		InstructorDetail tempDetail = entityManager.find(InstructorDetail.class, id);
		
		//if only want to delete record from instructor detail table, 
		//tempDetail.getInstructor().setInstructorDetail(null);
		
		entityManager.remove(tempDetail);
	}

	@Override
	public List<Course> findCoursesByInstructorId(int id) {

		TypedQuery<Course> query = entityManager.
				createQuery("from Course where instructor.id = :data", Course.class);
		query.setParameter("data", id);
		
		List<Course> coursesList = query.getResultList();
		return coursesList;
	}

	@Override
	public Instructor findInstructorWithCoursesJoinFetch(int id) {
		
		// by running following query, hibernate runs 2 queries. 1 for instructor and course. other for details.
//		TypedQuery<Instructor> query = entityManager.
//				createQuery("select i from Instructor i "
//						+ "JOiN FETCH i.courses "
//						+ "Where i.id = :data ",Instructor.class);
		
		// following query will reduce the hibernate query to 1.
		TypedQuery<Instructor> query = entityManager.
				createQuery("select i from Instructor i "
						+ "JOIN FETCH i.courses "
						+ "JOIN FETCH i.instructorDetail "
						+ "where i.id = :data ", Instructor.class);
		
		
		query.setParameter("data", id);
		
		Instructor tempInstructor = query.getSingleResult();
		
		return tempInstructor;
	}

	@Override
	public void update(Instructor tempInstructor) {
		entityManager.merge(tempInstructor);
	}

	@Override
	public void updateCourse(Course tempCourse) {
		entityManager.merge(tempCourse);
	}

	@Override
	public Course findCourseById(int id) {
		return entityManager.find(Course.class, id);
	}

	@Override
	public void deleteCourseById(int id) {
		
		Course tempCourse = entityManager.find(Course.class, id);
		entityManager.remove(tempCourse);
	}

	@Override
	public void saveCourse(Course course) {
		entityManager.persist(course);
	}

	@Override
	public Course findCourseAndReviewByCourseId(int id) {
		
		TypedQuery<Course> query = entityManager.
				createQuery("select c from Course c "
						+ "JOIN FETCH c.review "
						+ "where c.id = :data", Course.class);
		query.setParameter("data", id);
		
		Course course = query.getSingleResult();
		return course;
	}
}
