package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

	@Column(name = "grade")
	private Double grade;

	public Student() {
	}

	public Student(String name, String email, Double grade) {
		super(name, email);
		this.grade = grade;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}
}