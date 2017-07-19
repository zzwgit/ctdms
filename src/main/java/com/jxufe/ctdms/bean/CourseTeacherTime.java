package com.jxufe.ctdms.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 课程  - 班次 - 教师  - 上课时间地点
 * @author Moe
 *
 */
@Entity
@Table(name="course_teacher_time")
public class CourseTeacherTime {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	private Course course;
	
	@Column(length=10,nullable=false)
	private String shift;
	 
	@OneToOne(cascade=CascadeType.REFRESH) 
	private User user;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL) 
	private List<CourseTime> courseTimes;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CourseTime> getCourseTimes() {
		return courseTimes;
	}

	public void setCourseTimes(List<CourseTime> courseTimes) {
		this.courseTimes = courseTimes;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
