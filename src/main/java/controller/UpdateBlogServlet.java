package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

import beans.Blog;
import dao.BlogDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UpdateBlogServlet")
@MultipartConfig
public class UpdateBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    public UpdateBlogServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BlogDAO blogDAO = new BlogDAO();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String userId = request.getParameter("userId");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String existingImageUrl = request.getParameter("existingImageUrl");

            Part filePart = request.getPart("image_url");
            String fileName = null;
            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                filePart.write(uploadPath + File.separator + fileName);
            }

            Blog blog = new Blog();
            blog.setId(id);
            blog.setUserId(userId);
            blog.setTitle(title);
            blog.setContent(content);
            if (fileName != null) {
                blog.setImageUrl(UPLOAD_DIR + "/" + fileName);
            } else {
                blog.setImageUrl(existingImageUrl);
            }

            blogDAO.update(blog);

            response.sendRedirect("IndexServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp").forward(request, response);
        }
    }
}
