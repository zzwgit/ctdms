package com.jxufe.ctdms.service;

import java.util.List;

import com.jxufe.ctdms.bean.Notices;
import com.jxufe.ctdms.dto.NoticesDto;


public interface NoticesService {

	List<NoticesDto> getNoticetsByUserId(long userId);
	
	void addNewNotices(long userId,int level ,String title ,String message ,String noticesType);
}
