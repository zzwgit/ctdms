package com.jxufe.ctdms.dto;

import java.util.List;

import com.jxufe.ctdms.enums.DocState;

/**
 * 统一 jstl 的输出格式
 * @author Moe
 *
 */
public class DocDto {
 
	List<String> docInfos ;
	
	long id;
	
	int state ; 
	String stateMsg;
	String bgcolor;
	
	long docId;				//对应的文档id
	
	String name;
	
	String type;       //文档类型
	
	public DocDto() {
		 
	} 
	public DocDto(List<String> docInfos, int state , String name) {
		super();
		this.docInfos = docInfos;
		this.state = state; 
		this.name = name;

		DocState docstate = DocState.stateOf(state); 
		stateMsg = docstate.getStateMsg();
		bgcolor  = docstate.getPlusMsg();
	}
	public int getState() {
		return state;
	}
/**
 * 			.sign-bg-wait{  
			.sign-bg-pass-1{  
			.sign-bg-pass-2{  
			.sign-bg-fail{ 
 * @param state
 */
	public void setState(int state) {
		this.state = state; 
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public long getDocId() {
		return docId;
	}
	public void setDocId(long docId) {
		this.docId = docId;
	}
	public String getStateMsg() {
		return stateMsg;
	}
	public String getBgcolor() {
		return bgcolor;
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
