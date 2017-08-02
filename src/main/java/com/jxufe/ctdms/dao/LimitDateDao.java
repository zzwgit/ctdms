package com.jxufe.ctdms.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.LimitDate;

public interface LimitDateDao extends JpaRepository<LimitDate, Serializable>{

	
	LimitDate findByIsWork(int isWork);
	
	LimitDate findByTabAndIsWorkAndTerm(String tab,int isWork,long term);
}
