package ch.hevs.servlet;

import java.io.IOException;

import ch.hevs.bankservice.Bank;
import ch.hevs.businessobject.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BankServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4198967060157127023L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			// Selection of source and destination accounts
			
			Bank bank = new Bank();

			String src = request.getParameter("SRC");
			String dest = request.getParameter("DEST");
			int amount;

			amount = Integer.parseInt(request.getParameter("MONTANT"));

			if ( ! src.equals(dest)) {

				// Simple hypothesis: the account debited and credited is 
				// the first of the accounts of an owner

				Account compteSrc = bank.getClientByName(src).getAccounts()
						.get(0);
				Account compteDest = bank.getClientByName(dest).getAccounts()
						.get(0);

				// Transfer
				bank.transfer(compteSrc, compteDest, amount);
				request.setAttribute("result", "Transfer done");
			} else {
				request.setAttribute("result", "Error: accounts are identical!");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("result", "Wrong number format");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("result", "Exception:" + e.getMessage());
		}

		// Show result
		request.getRequestDispatcher("showTransferResult.jsp").forward(request,
				response);
	}
}
