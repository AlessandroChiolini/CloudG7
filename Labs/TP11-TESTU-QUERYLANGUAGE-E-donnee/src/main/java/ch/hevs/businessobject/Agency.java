package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Agency {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@OneToMany(mappedBy = "agency", cascade = { CascadeType.ALL })
	private Set<Banker> employees;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "agency", cascade = { CascadeType.ALL })
	private Set<Client> clients;

	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Banker> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Banker> employees) {
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	// constructors
	public Agency() {
		clients = new HashSet<Client>();
		employees = new HashSet<Banker>();
	}
	
	// helper methods
	public void addClient(Client c) {
		clients.add(c);
		c.setAgency(this);
	}
	public void addEmployee(Banker b) {
		employees.add(b);
		b.setAgency(this);
	}
}
