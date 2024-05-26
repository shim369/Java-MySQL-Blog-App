package controller;

import java.io.IOException;
import java.util.List;

import dao.BlogDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Blog;
import model.Login;
import model.LoginLogic;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//
		request.setCharacterEncoding( "UTF-8" );
		String userId = request.getParameter( "userId" );
		String pass = request.getParameter( "pass" );
		//
		Login login = new Login( userId, pass );
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		
		if( result ) {
			HttpSession session = request.getSession();
			session.setAttribute( "userId", userId );
			BlogDAO blogDAO = new BlogDAO();
	        List<Blog> blogList = blogDAO.findByUserId((String) request.getSession().getAttribute("userId"));

	        request.setAttribute("blogList", blogList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginUserPage.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect( "LoginServlet" );
		}
	}

}
