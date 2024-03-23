package com.hibernate.crud.advanceMappingOneToMany;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hibernate.crud.advanceMappingOneToMany.DAO.DAO;
import com.hibernate.crud.advanceMappingOneToMany.entity.Course;
import com.hibernate.crud.advanceMappingOneToMany.entity.Instructor;
import com.hibernate.crud.advanceMappingOneToMany.entity.InstructorDetail;
import com.hibernate.crud.advanceMappingOneToMany.entity.Review;

@SpringBootApplication
public class AdvanceMappingOneToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvanceMappingOneToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(DAO dao) {
		return runner -> {
			//following methods for one-to-many mapping (Bi-directional)
			//createInstructorWithCourses(dao);
			//findInstructorWithCourses(dao);
			//findCoursesForInstructor(dao);
			//findInstructorWithCoursesJoinFetch(dao);
			//updateInstructor(dao);
			//updateCourse(dao);
			//deleteInstructor(dao);
			//deleteCourse(dao);
			
			
			//following methods for one-to-many mapping (Uni-directional)
			//createCourseAndReview(dao);
			//findCourseAndReviewByCourseId(dao);
			deleteCourseAndReviews(dao);
			
			
			// following methods are used for one-to-one mapping.
			//createInstructor(dao);
			//findInstructor(dao);
			//deleteInstructor(dao);
			//findInstructorDetail(dao);
			//deleteInstructorDetail(dao);
		};
	}
	
	private void deleteCourseAndReviews(DAO dao) {

		int id = 3;
		System.out.println("Deleting course id :" + id);
		dao.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void findCourseAndReviewByCourseId(DAO dao) {

		int id=3;
		
		System.out.println("Finding Course id: " + id);
		Course tempCourse = dao.findCourseAndReviewByCourseId(id);
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReview());
		System.out.println("Done!");
	}

	private void createCourseAndReview(DAO dao) {
		
		Course tempCourse = new Course("Pacman - How to score 1 million points");
		
		tempCourse.add(new Review("Greate course.."));
		tempCourse.add(new Review("cool course.."));
		tempCourse.add(new Review("don't like it.."));
		
		System.out.println("Saving course and review.");
		dao.saveCourse(tempCourse);
		System.out.println("Done!");
	}

	private void deleteCourse(DAO dao) {
		
		int id = 2;
		System.out.println("Deleting Course id: " + id);
		dao.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void updateCourse(DAO dao) {

		int id = 1;
		System.out.println("Finding course id: " + id);
		Course tempCourse = dao.findCourseById(id);
		
		System.out.println("Updating course");
		tempCourse.setTitle("Guitar - The Ultimate Guide - updated");
		dao.updateCourse(tempCourse);
		System.out.println("Done!");
	}

	private void updateInstructor(DAO dao) {

		int id = 4;
		System.out.println("Finding instructor id: " + id);
		Instructor tempInstructor = dao.findInstructorById(id);
		
		System.out.println("Updating instructor");
		tempInstructor.setLastName("Tester");
		dao.update(tempInstructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(DAO dao) {
		
		int id = 4;
		
		System.out.println("Finding indtructor id: " + id);
		Instructor tempInstructor = dao.findInstructorWithCoursesJoinFetch(id);
		
		System.out.println("Instructor data: " + tempInstructor);
		System.out.println("Instrucotr Details: " + tempInstructor.getInstructorDetail());
		System.out.println("Associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!!");
	}

	private void findCoursesForInstructor(DAO dao) {
		
		int theId = 4;
		System.out.println("Finding Instructor is: " + theId);
		
		Instructor tempInstructor = dao.findInstructorById(theId);
		System.out.println("Temp Instructor: " + tempInstructor);
		System.out.println("Instructor Details: " + tempInstructor.getInstructorDetail());
		
		System.out.println("Finding courses.");
		List<Course> coursesList = dao.findCoursesByInstructorId(theId);
		
		tempInstructor.setCourses(coursesList);
		System.out.println("Courses: " + tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findInstructorWithCourses(DAO dao) {
		
		int theId = 4;
		System.out.println("Finding Instructor ID is: " + theId);
		
		Instructor tempInstructor = dao.findInstructorById(theId);
		
		System.out.println("Temp Instructor: " + tempInstructor);
		System.out.println("Instructor Details: " + tempInstructor.getInstructorDetail());
		System.out.println("Courses: " + tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(DAO dao) {
		
		//create an instructor
		Instructor tempInstructor = 
				new Instructor("Susan","Public","susan.public@gmail.com");
		
		InstructorDetail tempDetail = 
				new InstructorDetail("http://www.youtube.com", "Gammer");
		
		//add instructor details to instructor.
		tempInstructor.setInstructorDetail(tempDetail);
		
		Course tempCourse1 = new Course("Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball MasterClass");
		
		//add course details to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		
		//to save an instructor
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("Saving details: " + tempInstructor.getInstructorDetail());
		System.out.println("Saving courses: " + tempInstructor.getCourses());
		
		dao.save(tempInstructor);
	}

	private void deleteInstructorDetail(DAO dao) {

		int id = 3;
		System.out.println("Deleting instructor details of id: " + id);
		dao.deleteInstructorDetailById(id);
		System.out.println("Done.");
	}

	private void findInstructorDetail(DAO dao) {
		
		int id = 2;
		System.out.println("Finding instructor detail of id: " + id);
		InstructorDetail tempDetail = dao.findInstructorDetailById(id);
		
		System.out.println("instructor detail: " + tempDetail);
		System.out.println("the associated instructor: " + tempDetail.getInstructor());
		System.out.println("Done..");
	}

	private void deleteInstructor(DAO dao) {
		
		int id = 4;
		System.out.println("Deleting instructor of id: " + id);
		dao.deleteInstructorById(id);
		System.out.println("Done.");
	}

	private void findInstructor(DAO dao) {
		
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		
		Instructor tempInstructor = dao.findInstructorById(id);
		
		System.out.println("Instructor record: "+ tempInstructor);
		System.out.println("More details: " + tempInstructor.getInstructorDetail());
	}

	public void createInstructor(DAO dao) {
		
		//create the instructor
		Instructor tempInstructor = 
				new Instructor("Madhu","Patel","madhu.patel@gmail.com");
		
		//create the instructor details
		InstructorDetail tempDetail = 
				new InstructorDetail("http://www.luv2code.com/youtube","play Guitar");
		
		tempInstructor.setInstructorDetail(tempDetail);
		
		// save the instructor
		System.out.println("Saving instructor: " + tempInstructor);
		dao.save(tempInstructor);
		System.out.println("Done");
	}
}

