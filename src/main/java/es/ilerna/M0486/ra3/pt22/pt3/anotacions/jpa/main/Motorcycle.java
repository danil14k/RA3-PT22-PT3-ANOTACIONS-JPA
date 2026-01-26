package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "motorcycle")
public class Motorcycle extends Vehicle {

	@Column(name = "type")
	private String type;

	public Motorcycle() {
	}

	public Motorcycle(String brand, String type) {
		super(brand);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}