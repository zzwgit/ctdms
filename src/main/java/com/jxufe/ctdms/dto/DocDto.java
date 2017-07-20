package com.jxufe.ctdms.dto;

import java.util.List;

import com.jxufe.ctdms.bean.Course;

/**
 * 统一 jstl 的输出格式
 * @author Moe
 *
 */
public class DocDto {
 
	List<String> docInfos ;
	
	int state ; 
	
	String name;
	
	String type;       //文档类型
	
	public DocDto() {
		 
	} 
	public DocDto(List<String> docInfos, int state , String name) {
		super();
		this.docInfos = docInfos;
		this.state = state; 
		this.name = name;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<String> getDocInfos() {
		return docInfos;
	}

	public void setDocInfos(List<String> docInfos) {
		this.docInfos = docInfos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
