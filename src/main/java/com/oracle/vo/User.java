package com.oracle.vo;

public class User {
	String userName;
	String password;
	String phone;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", phone=" + phone + "]";
	}
	public User(String userName, String password, String phone) {
		super();
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}
	public User() {
		super();
	}
	
	
}
