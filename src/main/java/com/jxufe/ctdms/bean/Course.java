package com.jxufe.ctdms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	
	@ManyToOne
	@JoinColumn(name = "term_id")
	private Term term; // 学期
 
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
