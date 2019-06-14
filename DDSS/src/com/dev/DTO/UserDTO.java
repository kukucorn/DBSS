package com.dev.DTO;

public class UserDTO {
	String id;
	String pw;
	String name;
	boolean authority;
	String major;
	
	public UserDTO(String id, String pw, String name, boolean authority, String major) {

		this.id = id;
		this.pw = pw;
		this.name = name;
		this.authority = authority;
		this.major = major;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getAuthority() {
		return authority;
	}
	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
}
