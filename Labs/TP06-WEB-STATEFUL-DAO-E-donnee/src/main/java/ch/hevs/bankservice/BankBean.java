package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.dao.AccountDAO;
import ch.hevs.dao.ClientDAO;
import ch.hevs.exception.BankException;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.PostActivate;
import jakarta.ejb.PrePassivate;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

@Stateless
public class BankBean implements Bank {
	
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void transfer(Account source, Account destination, int amount)
			throws BankException {
		
		source.debit(amount);
		AccountDAO.saveOrUpdate(source);
		destination.credit(amount);
		AccountDAO.saveOrUpdate(destination);

	}

	public List<Client> getClients() throws BankException {
		List<Client> result;

		result = ClientDAO.getClients();

		return result;
	}

	public Client getClientByName(String nom) throws BankException {

		Client result = null;
		List<Client> clients = getClients();
		for (Client c : clients) {
			if (c.getLastname().equals(nom)) {
				result = c;
				break;
			}
		}
		return result;
	}

	public void storeNewClient(Client c) throws BankException {
		ClientDAO.save(c);
	}

	public void deleteClient(Client c) throws BankException {
		ClientDAO.delete(c);
	}

	public void modifyClient(Client c) throws BankException {
		ClientDAO.modify(c);
	}

	@PreDestroy
	public void end() {
		System.out.println("Destruction");
	}

	@PrePassivate
	public void beforePassivation() {
		System.out.println("Before Passivation");
	}

	@PostActivate
	public void afterPassivation() {
		System.out.println("After Passivation");
	}

}
