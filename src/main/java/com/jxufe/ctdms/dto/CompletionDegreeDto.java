package com.jxufe.ctdms.dto;

import java.util.List;

public class CompletionDegreeDto { 
	
	private String name;		//课程名
	private String code;		//课程代码
	private int state;			//课程状态
	
	private String cssClassName; // css 的class 名 ok - remove
	 
	private List<Schedule> schedules;	//每个课程对应的每个班的进度表

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCssClassName() {
		return cssClassName;
	}

	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
 
	
	
}
