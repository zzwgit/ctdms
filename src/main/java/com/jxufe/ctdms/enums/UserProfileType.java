package com.jxufe.ctdms.enums;

/**
 * 用户角色 枚举
 * @author Moe
 *
 */
public enum UserProfileType {
	USER(3,"USER"),
	TEACHER(1,"TEACHER"), 
	SECRETARY(6,"SECRETARY"),	//秘书
	DIRECTOR(11,"DIRECTOR"),	//系主任
	DEAN(16,"DEAN"),			//教学院长 
	
	
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