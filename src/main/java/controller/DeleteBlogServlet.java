package controller;

import java.io.IOException;

import dao.BlogDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteBlogServlet
 */
@WebServlet("/DeleteBlogServlet")
public class DeleteBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストから削除対象のブログIDを取得
        int blogId = Integer.parseInt(request.getParameter("blogId"));

        // BlogDAOを利用して削除処理を実行
        BlogDAO blogDAO = new BlogDAO();
        blogDAO.delete(blogId);

        // 削除が成功したことをクライアントに通知
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
