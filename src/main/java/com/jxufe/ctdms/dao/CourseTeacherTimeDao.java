package com.jxufe.ctdms.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.User;


public interface CourseTeacherTimeDao extends JpaRepository<CourseTeacherTime, Serializable> {

	List<CourseTeacherTime> findByUser(User user);
}
