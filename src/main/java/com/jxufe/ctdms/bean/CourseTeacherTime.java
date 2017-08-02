package com.jxufe.ctdms.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jxufe.ctdms.enums.DocState;

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
	
	@OneToOne(cascade=CascadeType.REMOVE)
	private Course course;
	
	@Column(nullable = false)
	private int state = DocState.NOT_SUBMIT.getStateId();  ;  // 每个班次的教学进度表的状态 
	
	@OneToOne(cascade=CascadeType.REMOVE)
	private UploadRecord uploadRecord;
	
	@ManyToOne
	@JoinColumn(name = "term_id")
	private Term term; // 学期
	
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

	@JsonIgnore
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
	
	@JsonIgnore
	public UploadRecord getUploadRecord() {
		return uploadRecord;
	}

	public void setUploadRecord(UploadRecord uploadRecord) {
		this.uploadRecord = uploadRecord;
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
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
