package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

	@Column(name = "salary")
	private Double salary;

	public Teacher() {
	}

	public Teacher(String name, String email, Double salary) {
		super(name, email);
		this.salary = salary;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
}