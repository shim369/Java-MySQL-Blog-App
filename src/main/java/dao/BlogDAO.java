package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Blog;

public class BlogDAO {
    private final String JDBC_URL = "jdbc:mysql://localhost/javablog?characterEncoding=UTF-8&serverTimezone=Asia/Tokyo";
    private final String DB_USER = "root";
    private final String DB_PASS = "shimshim";

    public boolean create(Blog blog) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO blogs (user_id, title, content) VALUES (?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, blog.getUserId());
            pStmt.setString(2, blog.getTitle());
            pStmt.setString(3, blog.getContent());

            int result = pStmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Blog> findByUserId(String userId) {
        List<Blog> blogList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return blogList;
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT id, title, content, created_at FROM blogs WHERE user_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);

            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String createdAt = rs.getString("created_at");

                Blog blog = new Blog(id, userId, title, content, createdAt);
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blogList;
    }
    
    public List<Blog> findAllByUserId(String userId) {
        List<Blog> blogList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT id, user_id, title, content, created_at FROM blogs WHERE user_id = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                pStmt.setString(1, userId);
                try (ResultSet rs = pStmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String content = rs.getString("content");
                        String createdAt = rs.getString("created_at");
                        Blog blog = new Blog(id, userId, title, content, createdAt);
                        blogList.add(blog);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogList;
    }
    
    public List<Blog> findAll() {
        List<Blog> blogList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT id, user_id, title, content, created_at FROM blogs";
            try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pStmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String userId = rs.getString("user_id");
                        String title = rs.getString("title");
                        String content = rs.getString("content");
                        String createdAt = rs.getString("created_at");
                        Blog blog = new Blog(id, userId, title, content, createdAt);
                        blogList.add(blog);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogList;
    }


}
