package controller;

import java.io.File;
import java.io.IOException;
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

    public UpdateBlogServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BlogDAO blogDAO = new BlogDAO();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String userId = request.getParameter("userId");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Part filePart = request.getPart("image_url");

            if (filePart == null) {
                System.out.println("File part is null");
            } else {
                System.out.println("File part is not null");
                System.out.println("File name: " + getSubmittedFileName(filePart));
                System.out.println("File size: " + filePart.getSize());
            }

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

            String imageUrl = "uploads/" + fileName;
            Blog blog = new Blog(id, userId, title, content, imageUrl);

            blogDAO.update(blog);

            response.sendRedirect("IndexServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/jsp/loginUser.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
