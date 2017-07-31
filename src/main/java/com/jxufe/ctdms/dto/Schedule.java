package com.jxufe.ctdms.dto;

/**
 * 用于 CompletionDegreeDTO
 * 教学进度表 
 * @author Moe
 *
 */
public class Schedule {
	private String shift;
	private int state;
	private String teacherName;
	private String cssClassName;
	
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCssClassName() {
		return cssClassName;
	}
	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}
	 

}
