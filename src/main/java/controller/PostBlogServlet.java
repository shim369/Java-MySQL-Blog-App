package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import beans.Blog;
import dao.BlogDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class PostBlogServlet
 */
@WebServlet("/PostBlogServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
	    maxFileSize = 1024 * 1024 * 10,       // 10 MB
	    maxRequestSize = 1024 * 1024 * 50     // 50 MB
)
public class PostBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PostBlogServlet.class.getName());

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
        String userId = request.getParameter("userId");
        if (userId == null) {
            // ログインしていない場合はログインページにリダイレクト
            response.sendRedirect("LoginServlet");
            return;
        }

        // ユーザーのブログ一覧を取得
        BlogDAO blogDAO = new BlogDAO();
        List<Blog> blogList = blogDAO.findByUserId(userId);

        // JSPに渡す
        request.setAttribute("blogList", blogList);
        request.setAttribute("userId", userId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginUser.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    String userId = (String) request.getSession().getAttribute("userId");
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    Part filePart = request.getPart("image_url");
	    String fileName = getSubmittedFileName(filePart);
	    String filePath = null;

	    if (filePart != null && filePart.getSize() > 0) {
	        String uploadDir = getServletContext().getRealPath("/uploads");
	        File uploadDirFile = new File(uploadDir);
	        if (!uploadDirFile.exists()) {
	            uploadDirFile.mkdirs();
	        }
	        filePath = uploadDir + File.separator + fileName;
	        filePart.write(filePath);
	    } else {
	        fileName = "dummy.png";
	    }

	    String imageUrl = "uploads/" + fileName; // Set imageUrl
	    Blog blog = new Blog(userId, title, content, imageUrl);
	    BlogDAO blogDAO = new BlogDAO();
	    boolean result = blogDAO.create(blog);

	    if (result) {
	        LOGGER.info("ブログの投稿に成功しました。");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/blogSuccess.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        LOGGER.warning("ブログの投稿に失敗しました。");
	        request.setAttribute("errorMsg", "ブログの投稿に失敗しました。");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp");
	        dispatcher.forward(request, response);
	    }

	    if (filePart != null && filePart.getSize() > 0) {
	        LOGGER.info("画像の保存に成功しました。保存先: " + filePath);
	    } else {
	        LOGGER.warning("画像がアップロードされていないため、ダミー画像が使用されました。");
	    }
	}
	
    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
