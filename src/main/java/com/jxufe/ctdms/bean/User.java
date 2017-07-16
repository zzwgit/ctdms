package com.jxufe.ctdms.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	@Column(length=25,nullable=false)
	private String userName;
	@Column(length=25,nullable=false)
	private String passWord;
	@Column(length=25,nullable=false)
	private String realName; 
	@Column(length=25,nullable=false)
	private String roleName; 
	
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
 
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
}
