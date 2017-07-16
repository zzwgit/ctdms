package com.jxufe.ctdms.dto;

import java.util.List;

import com.jxufe.ctdms.bean.Notices;

public class NoticesDto {

	private String typeName;
	
	private List<Notices> notices;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Notices> getNotices() {
		return notices;
	}

	public void setNotices(List<Notices> notices) {
		this.notices = notices;
	}
	
	
}
