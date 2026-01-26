package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plane")
public class Plane extends Vehicle {

	@Column(name = "capacity")
	private Integer capacity;

	public Plane() {
	}

	public Plane(String brand, Integer capacity) {
		super(brand);
		this.capacity = capacity;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}