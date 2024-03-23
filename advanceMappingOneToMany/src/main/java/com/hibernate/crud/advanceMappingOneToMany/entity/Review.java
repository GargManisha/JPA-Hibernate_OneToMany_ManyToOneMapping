package com.hibernate.crud.advanceMappingOneToMany.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name="review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String comemnt;

	@Override
	public String toString() {
		return "Review [id=" + id + ", comemnt=" + comemnt + "]";
	}

	public Review(String comemnt) {
		this.comemnt = comemnt;
	}

	public Review() {
	}
	
}
