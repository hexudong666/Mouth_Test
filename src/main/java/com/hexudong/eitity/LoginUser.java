package com.hexudong.eitity;

public class LoginUser {
	private Integer uid;
	private String uname;
	private String pwd;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "LoginUser [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd + "]";
	}
}
