package com.jxufe.ctdms.utils;

import java.util.List;

import com.jxufe.ctdms.bean.Course;
import com.jxufe.ctdms.bean.CourseTime;

public class ExcelBean {

	private String courseName="";
	private String teacherName="";
	private String courseCode=""; 
	private String shift="";
	private List<CourseTime> courseTimes;
	private String district="";		//校区
	private String weekly="";
	public Course toCourse(){
		Course course = new Course();
		course.setCourseName(courseName);
		course.setCourseCode(courseCode);
		course.setDistrict(district); 
		course.setWeekly(weekly); 
		return course;
	}
	@Override
	public String toString() { 
		return courseCode+"-["+shift+"]:"+courseName+":"+teacherName;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}   
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name) {
		this.courseName = name;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	} 
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getWeekly() {
		return weekly;
	}
	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}
	public List<CourseTime> getCourseTimes() {
		return courseTimes;
	}
	public void setCourseTimes(List<CourseTime> courseTimes) {
		this.courseTimes = courseTimes;
	}  
	
}
