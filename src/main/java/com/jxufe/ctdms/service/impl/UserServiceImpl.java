package com.jxufe.ctdms.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.bean.UserProfile;
import com.jxufe.ctdms.dao.CourseTeacherTimeDao;
import com.jxufe.ctdms.dao.UserDao;
import com.jxufe.ctdms.dto.DocDto;
import com.jxufe.ctdms.enums.State;
import com.jxufe.ctdms.enums.UserProfileType;
import com.jxufe.ctdms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	PasswordEncoder passwordEncoder;
	//http://127.0.0.1:8080/CTDMS-ssh/register?userName=admin&passWord=123&realName=%E7%AE%A1%E7%90%86%E5%91%98
	@Autowired
	CourseTeacherTimeDao courseTeacherTimeDao;
	@Override
	public User register(User user) { 
		try{
			setUserInfo(user);
		}catch(Exception e){ 
			e.printStackTrace();
			return null;
		}
		user = userDao.save(user);
		System.out.println("注册    "+user); 
		return user; 
	}
	private void setUserInfo(User user) { 
		//分配权限  默认用户
		Set<UserProfile> userProfiles = new HashSet<>(); 
		userProfiles.add(new UserProfile(UserProfileType.TEACHER.getProfileTypeId(),UserProfileType.TEACHER.getUserProfileType()));
		userProfiles.add(new UserProfile(UserProfileType.USER.getProfileTypeId(),UserProfileType.USER.getUserProfileType()));
		user.setUserProfiles(userProfiles);  
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));  
	}
	@Override
	public void registerTearchers(List<User>users){
		for (User user : users) {
			setUserInfo(user);
		}
		userDao.save(users);
	}
	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
	@Override
	public List<User> findAll() { 
		List<User> users =  userDao.findAll();
		Collections.sort(users, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o2.getLoginTimes()-o1.getLoginTimes();
            }
		}); 
		return users;
	}
	@Override
	public List<CourseTeacherTime> getCTT(long userId) { 
		User user = userDao.findOne(userId);
		List<CourseTeacherTime> ctts = courseTeacherTimeDao.findByUser(user); 
		return ctts;
	}
	@Override
	public void forbid(long uid,int forbid) { 
		User user = userDao.findOne(uid);
		if(forbid==1){
			user.setState(State.LOCKED.getState());
		}else{
			user.setState(State.ACTIVE.getState());
		}
		userDao.save(user); 
	}
	@Override
	public void delete(long uid) {
		userDao.delete(uid);
	}
	@Override
	public void modifyPassword(long uid, String passWord) {
		User user = userDao.findOne(uid);
		user.setPassWord(passwordEncoder.encode(passWord));  
		userDao.save(user); 
	}
	@Override
	public void modifyInfo(long uid, User user) {
		User old = userDao.findOne(uid);
		old.setRealName(user.getRealName());
		old.setUserName(user.getUserName());
		userDao.save(old); 
	}

}
