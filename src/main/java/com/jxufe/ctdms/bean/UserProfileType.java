package com.jxufe.ctdms.bean;

/**
 * 用户角色 枚举
 * @author Moe
 *
 */
public enum UserProfileType {
	USER(3,"USER"),
	TEACHER(1,"TEACHER"), 
	ADMIN(999,"ADMIN");
	
	String userProfileType;

	private int profileTypeId; 
	
	UserProfileType(int id,String msg){
		this.profileTypeId = id;
		this.userProfileType = msg ;
	}
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	public int getProfileTypeId() {
		return profileTypeId;
	}
	
}