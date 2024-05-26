package controller;

import java.io.IOException;

import dao.BlogDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blog;

/**
 * Servlet implementation class PostBlogServlet
 */
@WebServlet("/PostBlogServlet")
public class PostBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostBlogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginUserPage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String userId = (String) request.getSession().getAttribute("userId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Blog blog = new Blog(userId, title, content);
		BlogDAO blogDAO = new BlogDAO();
		boolean result = blogDAO.create(blog);

		if (result) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/blogSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorMsg", "ブログの投稿に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
			dispatcher.forward(request, response);
		}
	}

}
