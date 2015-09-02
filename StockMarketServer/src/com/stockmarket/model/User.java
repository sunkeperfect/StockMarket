package com.stockmarket.model;


public class User {
	int id;
	String username;
	String password;
	String email;
	String user_token;
	String device_id;
	public User() {
	}
	public User(int id, String username, String password, String email, String user_token) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.user_token = user_token;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	@com.fasterxml.jackson.annotation.JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_token() {
		return user_token;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", user_token=" + user_token + "]";
	}
	
}
