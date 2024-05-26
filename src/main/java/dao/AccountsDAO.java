package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountsDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/javablog?characterEncoding=UTF-8&serverTimezone=Asia/Tokyo";
	private final String DB_USER = "root";
	private final String DB_PASS = "shimshim";

	public Account findByLogin(Login login) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		Account account = null;
		// MySQL に接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文を準備
			String sql = "select user_id, pass, mail, name, age from accounts where user_id = ? and pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);// SQL文をデータベースに送るためのStatement生成
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, login.getPass());
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				String userId = rs.getString("user_id");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				account = new Account(userId, pass, mail, name, age);
				// System.out.println( "ID:"+id + " , "+"名前:" + name + " , " +"テキスト:"+ text );
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}

	public boolean create(Account account) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

	    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        // 既存ユーザーIDの確認
	        String checkSql = "SELECT COUNT(*) FROM accounts WHERE user_id = ?";
	        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	        checkStmt.setString(1, account.getUserId());
	        ResultSet rs = checkStmt.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            // 既に同じユーザーIDが存在する場合
	            return false;
	        }

	        // 新規ユーザーの登録
	        String sql = "INSERT INTO accounts (user_id, pass, mail, name, age) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, account.getUserId());
	        pStmt.setString(2, account.getPass());
	        pStmt.setString(3, account.getMail());
	        pStmt.setString(4, account.getName());
	        pStmt.setInt(5, account.getAge());

	        int result = pStmt.executeUpdate();
	        return result == 1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
