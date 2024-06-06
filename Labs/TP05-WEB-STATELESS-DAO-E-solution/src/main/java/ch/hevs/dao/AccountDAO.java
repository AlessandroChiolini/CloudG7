package ch.hevs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.dao.util.ConnexionManager;
import ch.hevs.exception.BankException;


public class AccountDAO {

	public static void saveOrUpdate2(Account c) throws BankException, InterruptedException, SQLException {
		boolean failure = (Math.random() > 0.2);
		saveOrUpdate(c);

		if (failure) {

			// A glimpse inside the transaction (read uncommitted)
			System.out.println("Table COMPTE while being modified by the transaction (read uncommitted):");
			displayCOMPTE();
			
			TimeUnit.SECONDS.sleep(5); // wait 5 sec.
			System.out.println(" [Simulation] - An exception has just been generated by the database after the update of the account " + c.getId());

			throw new BankException("simulation of a technical problem");
		}
	}
	
	public static void displayCOMPTE() throws SQLException {
		
		Connection connection = ConnexionManager.getConnexion();
    	String sql = "SELECT * FROM COMPTE";
		
		PreparedStatement pSt = connection.prepareStatement(sql);
		ResultSet resultSet = pSt.executeQuery();
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(",  ");
		        String columnValue = resultSet.getString(i);
		        System.out.print(rsmd.getColumnName(i)+ ": " + columnValue);
		    }
		    System.out.println("");
		}
		
	}

	public static void saveOrUpdate(Account c) throws BankException {
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();

			// remark:
			// the column id has been declared as 'identity' in the database
			// it will be autoincremented: specific hsqldb
			if (c.getId() == null) {

				PreparedStatement pSt = connexion
						.prepareStatement("INSERT INTO COMPTE (NUMERO, SOLDE, DESCRIPTION, FK_CLIENT) VALUES (NULL, ? , ? , ?)");
				pSt.setString(1, c.getNumber());
				pSt.setLong(2, c.getSaldo());
				pSt.setLong(3, c.getOwner().getId());
				pSt.setString(4, c.getDescription());
				pSt.executeUpdate();

				pSt = connexion
						.prepareStatement("SELECT ID FROM CLIENT ID WHERE NOM=? AND PRENOM=? AND DESCRIPTION=?");
				pSt.setString(1, c.getNumber());
				pSt.setLong(2, c.getSaldo());
				pSt.setLong(3, c.getOwner().getId());

				ResultSet rs = pSt.executeQuery();

				if (!rs.next()) {
					throw new BankException("impossible to get the Id !");
				} else {
					c.setId(rs.getLong("ID"));
				}
				System.out
						.println("AccountDAO:: account inserted in the data with Id "
								+ c.getId());

			} else {
				// Simple modification: we should here implement a mecanism of "dirty checking":
				// only modification on the saldo are saved
				PreparedStatement pSt = connexion
						.prepareStatement("UPDATE  COMPTE SET SOLDE=? WHERE ID=?");
				pSt.setLong(1, c.getSaldo());
				pSt.setLong(2, c.getId());

				pSt.executeUpdate();

			}
		} catch (SQLException e) {
			throw new BankException(e);
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static List<Account> getAccounts(Client client) throws BankException {
		List<Account> result = new ArrayList<Account>();
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();
			Statement St = connexion.createStatement();
			ResultSet rs = St
					.executeQuery("SELECT * FROM COMPTE WHERE FK_CLIENT='"
							+ client.getId() + "'");

			Account c;
			while (rs.next()) {
				c = new Account();
				c.setId(rs.getLong("ID"));
				c.setNumber(rs.getString("NUMERO"));
				c.setSaldo(rs.getLong("SOLDE"));
				c.setOwner(client);
				result.add(c);
			}
		} catch (SQLException e) {
			throw new BankException(e);
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	public static void delete(Account c) throws BankException {
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();
			PreparedStatement pSt = connexion
					.prepareStatement("DELETE FROM COMPTE WHERE ID=?");
			pSt.setLong(1, c.getId());
			pSt.executeUpdate();
			System.out.println("AccountDAO::Account " + c.getId() + " deleted");
		} catch (SQLException e) {
			throw new BankException(e);
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}