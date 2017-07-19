package com.jxufe.ctdms.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxufe.ctdms.bean.Term;
import com.jxufe.ctdms.dao.TermDao;
import com.jxufe.ctdms.service.TermService;

@Service
public class TermServiceImpl implements TermService{

	@Autowired
    TermDao termDao;
	
	@PostConstruct
	public void initTerm(){  
			List<Term> terms = termDao.findAll();
			nowTerm = terms.get(terms.size() - 1); 
	}
	
	private static Term nowTerm;
	public static Term getNowTerm(){
		return nowTerm; 
	} 
	
}
