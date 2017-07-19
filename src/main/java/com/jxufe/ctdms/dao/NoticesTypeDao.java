package com.jxufe.ctdms.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.NoticesType;

public interface NoticesTypeDao  extends JpaRepository<NoticesType, Serializable>{
		   
	 NoticesType findByTypeName(String typeName);
	   
	 
}
