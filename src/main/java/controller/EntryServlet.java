package controller;

import java.io.IOException;

import beans.Account;
import dao.AccountsDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EntryLogic;

/**
 * Servlet implementation class EntryServlet
 */
@WebServlet("/EntryServlet")
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entry.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntryLogic entryLogic = new EntryLogic();

		request.setCharacterEncoding("UTF-8");
		boolean isValid = entryLogic.execute(request);

		if (isValid) {
			String userId = request.getParameter("userId");
			String pass = request.getParameter("pass");
			String mail = request.getParameter("mail");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));

			Account account = new Account(userId, pass, mail, name, age);

			AccountsDAO dao = new AccountsDAO();
			boolean result = dao.create(account);

			if (result) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				session.setAttribute("account", account);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entryResult.jsp");
				dispatcher.forward(request, response);
	        } else {
	            request.setAttribute("errorMsg", "登録に失敗しました。ユーザーIDが既に存在します。");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entry.jsp");
	            dispatcher.forward(request, response);
	        }
		} else {
			request.setAttribute("errorMsg", "入力内容が正しくありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/entry.jsp");
			dispatcher.forward(request, response);
		}
	}

}
