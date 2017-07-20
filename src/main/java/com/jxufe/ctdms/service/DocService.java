package com.jxufe.ctdms.service;

import java.util.List;

import com.jxufe.ctdms.dto.DocDto;

public interface DocService {

	void parseExcel();
	
	List<DocDto> getWaitSubDocByTab(String tab,long userId);
}
