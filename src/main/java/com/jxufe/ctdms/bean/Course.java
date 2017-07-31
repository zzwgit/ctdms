package com.jxufe.ctdms.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jxufe.ctdms.enums.DocState;
import com.jxufe.ctdms.utils.ExcelBean;

/**
 * @author 叶志伟 作者 E-mail: 1059654342@qq.com date 创建时间：2017年4月17日 下午10:31:40
 * @version 1.0
 */
@Entity
@Table
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(nullable = false)
	private int state = DocState.NOT_SUBMIT.getStateId();  // 教学大纲的状态 
	@ManyToOne
	@JoinColumn(name = "term_id")
	private Term term; // 学期
 
	@OneToOne(cascade=CascadeType.REFRESH)
	private UploadRecord uploadRecord;
	 
	@Column(length = 25, nullable = false)
	private String courseName;
	@Column(length = 10, nullable = false, unique = true)
	private String courseCode; // code代码
	@Column(length = 25, nullable = true)
	private String district; // 校区
	@Column(length = 10, nullable = true)
	private String weekly; // 周次

	@Override
	public String toString() {
		return courseCode + "-["  + "]:" + courseName + ":"
				 + ":";
	}
 
	@Override
	public boolean equals(Object obj) {  
		if (this == obj)
			return true;
		if (obj == null)
			return false; 
		if(obj instanceof ExcelBean){
			if(this.courseCode.equals(((ExcelBean)obj).getCourseCode())){
				return true;
			}
			return false;
		}
		if (obj instanceof String){ 
			if(this.courseCode.equals((String)obj)){
				return true;
			}
			return false;
		}
		Course other = (Course) obj;
		if (courseCode == null) {
			if (other.courseCode != null)
				return false;
		} else if (!courseCode.equals(other.courseCode))
			return false;
		return true;
	}

	public UploadRecord getUploadRecord() {
		return uploadRecord;
	}

	public void setUploadRecord(UploadRecord uploadRecord) {
		this.uploadRecord = uploadRecord;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	@JsonIgnore
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
 
 
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String name) {
		this.courseName = name;
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

}
