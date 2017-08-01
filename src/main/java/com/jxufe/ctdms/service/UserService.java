package com.jxufe.ctdms.service;

import java.util.List;

import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.User;

public interface UserService {
 
	User register(User user);
	
	User findByUserName(String userName);

	void registerTearchers(List<User>users);
	
	List<User> findAll();
	
	List<CourseTeacherTime> getCTT(long userId);

	void forbid(long uid, int forbid);

	void delete(long uid);

	void modifyPassword(long uid, String passWord);

	void modifyInfo(long uid, User user);
}
