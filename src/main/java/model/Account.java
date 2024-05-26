package model;

public class Account {
	private String userId;
	private String pass;
	private String mail;
	private String name;
	private int age;

	public Account() {}
	public Account( String userId, String pass, String mail, String name, int age) {
		setUserId( userId);
		setPass( pass);
		setMail( mail);
		setName( name);
		setAge( age);
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
