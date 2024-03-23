package com.hibernate.crud.advanceMappingOneToMany.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "instructor")
@Data
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_Name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Instructor() {
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", "
				+ "lastName=" + lastName + ", email=" + email + "]";
	}
	
	//add one-to-many mapping for list of courses.
	// for lazy implementation, changed Eager to lazy
	@OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Course> courses;
	
	//Add a method for bi-directional relationship 
	//between instructor and course.
	
	public void add(Course tempCourse) {
		if(courses == null ) {
			courses = new ArrayList<>();
		}
		
		//add this course to list of courses.
		courses.add(tempCourse);
		
		//to set bi-directional relationship
		tempCourse.setInstructor(this);
	}
}



