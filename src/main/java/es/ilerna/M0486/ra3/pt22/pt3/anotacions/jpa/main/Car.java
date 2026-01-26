package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car extends Vehicle {

	@Column(name = "seats")
	private Integer seats;

	public Car() {
	}

	public Car(String brand, Integer seats) {
		super(brand);
		this.seats = seats;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}
}