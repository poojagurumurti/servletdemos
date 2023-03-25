package com.hibernatehelloworld.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="enrollment_id")
	private String enrollmentId;
	private String name;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="guide_id")
	private Guide guide;   //foreign key-guide_id which maps to Id in Guide table  
	
	public Student() {}
	public Student(String enrollmentId, String name, Guide guide) {
		super();
		this.enrollmentId = enrollmentId;
		this.name = name;
		this.guide = guide;
	}
	
}
