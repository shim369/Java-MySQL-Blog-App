package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Blog;
import dao.BlogDAO;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/IndexServlet", asyncSupported = true)
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(IndexServlet.class.getName());

    public IndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.start(() -> {
            try {
                BlogDAO blogDAO = new BlogDAO();
                List<Blog> blogList = blogDAO.findAll();
                
                // デバッグ用ログ出力
                LOGGER.log(Level.INFO, "Fetched blog list: {0}", blogList);

                // リクエストにデータを設定
                request.setAttribute("blogList", blogList);

                // 非同期処理完了後にフォワードを行う
                asyncContext.dispatch("/WEB-INF/jsp/index.jsp");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error in async process", e);
                try {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error occurred");
                } catch (IOException ioException) {
                    LOGGER.log(Level.SEVERE, "Failed to send error response", ioException);
                }
            } 
        });
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
