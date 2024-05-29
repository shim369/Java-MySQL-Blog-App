package model;

import beans.Account;
import beans.Login;
import dao.AccountsDAO;

public class LoginLogic {
	public boolean execute( Login login ) {
		AccountsDAO dao = new AccountsDAO();
		Account account = dao.findByLogin( login );
	//	System.out.println( account != null );
		return account != null;
	}
}
