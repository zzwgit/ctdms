package com.jxufe.ctdms.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.Course;
import com.jxufe.ctdms.bean.Term;

public interface CourseDao extends JpaRepository<Course, Serializable>{

	List<Course> findByTerm(Term term);
	
	List<Course> findByState(int state);
}
