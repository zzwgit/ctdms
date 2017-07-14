package com.jxufe.ctdms.bean;
 
public class User {
	private int userId;
	private String userName;
	private String passWord;
	private String realName; 
	private LoginInfo loginInfo;  
	@Override
	public String toString() {
		return userId+"-"+userName+"-"+passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	} 
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	} 

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
}
