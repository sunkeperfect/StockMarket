package com.stockmarket.model;


public class User {
	int id;
	String username;
	String password;
	String email;
	String user_token;
	String device_id;
	/**
	 * 临时用户标示
	 * 使用divice_id 会在用户注册后释放
	 */
	String temp_id;
	public User() {
	}
	public User(int id, String username, String password, String email, String user_token,String device_id,String temp_id) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.user_token = user_token;
		this.device_id=device_id;
		this.temp_id=temp_id;
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

	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
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
	public String getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", user_token=" + user_token + ", device_id=" + device_id + ", temp_id=" + temp_id + "]";
	}


	
}
