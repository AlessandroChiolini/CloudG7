package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Client extends Person {

	private String description;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "owners")
	private Set<InternalAccount> internalAccounts;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CLIENT")
	private Set<ExternalAccount> externalAccounts;
	@Embedded
	private Address address;
	@ManyToOne
	private Agency agency;

	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address adresse) {
		this.address = adresse;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ExternalAccount> getExternalAccounts() {
		return externalAccounts;
	}
	public void setExternalAccounts(Set<ExternalAccount> externalAccounts) {
		this.externalAccounts = externalAccounts;
	}

	public Set<InternalAccount> getInternalAccounts() {
		return internalAccounts;
	}
	public void setInternalAccounts(Set<InternalAccount> internalAccounts) {
		this.internalAccounts = internalAccounts;
	}

	// constructors
	public Client() {
		super();
	}
	public Client(String lastname, String firstname, String description) {
		super(lastname, firstname);
		this.description = description;
		this.internalAccounts = new HashSet<InternalAccount>();
		this.externalAccounts = new HashSet<ExternalAccount>();
	}

	// helper methods
	public void addInternalAccount(InternalAccount c) {
		internalAccounts.add(c);
		c.addOwner(this);
	}
	public void addExternalAccount(ExternalAccount c) {
		externalAccounts.add(c);
	}
}
