package com.jxufe.ctdms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.bean.UserProfile;
import com.jxufe.ctdms.dao.UserDao;
 

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		User user = userDao.findByUserName(userName);
		System.out.println("["+userName+"] 尝试登陆");
		if(user==null){ 
			throw new UsernameNotFoundException("Username not found");
		}
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), 
				 user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfile userProfile : user.getUserProfiles()){
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType())); 
		}
		//System.out.println("authorities :"+authorities);
		return authorities;
	}

}
