package ch.hevs.bankservice;

import java.sql.SQLException;
import java.util.List;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.dao.AccountDAO;
import ch.hevs.dao.ClientDAO;
import ch.hevs.exception.BankException;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;


@Stateless
public class BankBean implements Bank {
	

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void transfer(Account source, Account destination, int amount)
			throws BankException, InterruptedException, SQLException {
		source.debit(amount);
		AccountDAO.saveOrUpdate(source);
		destination.credit(amount);
		AccountDAO.saveOrUpdate2(destination);
	}

	public List<Client> getClients() throws BankException {
		List<Client> result;

		result = ClientDAO.getClients();

		return result;
	}

	public Client getClientByName(String name) throws BankException {

		Client result = null;
		List<Client> clients = getClients();
		for (Client c : clients) {
			if (c.getLastname().equals(name)) {
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

}
