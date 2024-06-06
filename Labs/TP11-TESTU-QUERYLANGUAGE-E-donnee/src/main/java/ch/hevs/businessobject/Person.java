package ch.hevs.businessobject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private String lastname;
	private String firstname;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String nom) {
		this.lastname = nom;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// constructors
	public Person() {
	}
	public Person(String lastname, String firstname) {
		this.lastname = lastname;
		this.firstname = firstname;
	}
}
