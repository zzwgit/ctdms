package com.jxufe.ctdms.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.User;

public interface UserDao extends JpaRepository<User, Serializable>{

	User findByUserId(long userId);
}
