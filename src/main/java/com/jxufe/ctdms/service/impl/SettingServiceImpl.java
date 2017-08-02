package com.jxufe.ctdms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxufe.ctdms.bean.LimitDate;
import com.jxufe.ctdms.dao.LimitDateDao;
import com.jxufe.ctdms.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	LimitDateDao limitDateDao;
	
	
	@Override
	public void setLimitDate(LimitDate limitDate) {
		LimitDate old = limitDateDao.findByTabAndIsWorkAndTerm(limitDate.getTab(), 1, TermServiceImpl.getNowTerm().getTermId());
		if(old!=null){
			old.setIsWork(0);
			limitDateDao.save(old);
		}
		limitDate.setIsWork(1);
		limitDate.setTerm(TermServiceImpl.getNowTerm().getTermId());
		limitDateDao.save(limitDate);
		System.out.println(limitDate);
	}

}
