package beans;

public class Login {
	private String userId;
	private String pass;

	public Login() {}
	public Login( String userId, String pass ) {
		setUserId( userId);
		setPass( pass);
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
