package com.jxufe.ctdms.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.Notices;
 

public interface NoticesDao  extends JpaRepository<Notices, Serializable>{
	
	

}
