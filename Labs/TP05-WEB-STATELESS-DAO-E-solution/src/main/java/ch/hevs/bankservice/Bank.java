package ch.hevs.bankservice;

import java.sql.SQLException;
import java.util.List;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.exception.BankException;
import jakarta.ejb.Local;


@Local
public interface Bank {

	public List<Client> getClients() throws BankException;

	public Client getClientByName(String nom) throws BankException;

	public void storeNewClient(Client c) throws BankException;

	public void deleteClient(Client c) throws BankException;

	public void modifyClient(Client c) throws BankException;

	public void transfer(Account source, Account destination, int montant)
			throws BankException, InterruptedException, SQLException;

}
