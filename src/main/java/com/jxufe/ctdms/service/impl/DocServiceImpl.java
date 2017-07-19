package com.jxufe.ctdms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxufe.ctdms.bean.Course;
import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.Term;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dao.CourseDao;
import com.jxufe.ctdms.dao.CourseTeacherTimeDao;
import com.jxufe.ctdms.dao.UserDao;
import com.jxufe.ctdms.service.DocService;
import com.jxufe.ctdms.service.UserService;
import com.jxufe.ctdms.utils.ExcelBean;
import com.jxufe.ctdms.utils.ExcelParse;
@Service
public class DocServiceImpl implements DocService {

	@Autowired
	UserService userService;
	@Autowired
	CourseDao courseDao;
	@Autowired
	UserDao userDao;
	@Autowired
	CourseTeacherTimeDao courseTeacherTimeDao;
	@Override
	public void parseExcel() { 
		List<ExcelBean> ebs = ExcelParse.parse("E:\\QQ\\QQmessage\\1059654342\\FileRecv\\软通学院本科162学期课表_撤班后.xls");
		Term term = TermServiceImpl.getNowTerm();
		//准备单独的课程  添加到数据库
		//List<Course> singeCourses = getSingeCourseList(ebs,term);
		//System.out.println(singeCourses);
		//courseDao.save(singeCourses);
		List<Course> dbCourses = courseDao.findByTerm(term);
		List<CourseTeacherTime> ctts = new ArrayList<>();
		List<Course> saveCourses =  new ArrayList<>();		//准备保存的
		
		List<User> dbUsers = userDao.findAll();
		List<User> saveUsers =  new ArrayList<>();		//准备保存的
		
		for (ExcelBean eb : ebs) {
			CourseTeacherTime ctt =  new CourseTeacherTime();
			//检查数据库是否有相同的
			Course course = null ;

			//setEqualsObj(Course.class,saveCourses,course,true,eb.getCourseCode());
			//setEqualsObj(Course.class,dbCourses,course,false,eb.getCourseCode());
			
			for (Course c : saveCourses) {
				if(eb.getCourseCode().equals(c.getCourseCode())){
					course = c ;
					break;
				}
			}
			if(course == null){
				for (Course c : dbCourses) {
					if(eb.getCourseCode().equals(c.getCourseCode())){
						course = c ;
						break;
					}
				}
			}
			if(course == null){
				course = eb.toCourse(); 
				course.setTerm(term);
				courseDao.save(course);
				saveCourses.add(course);
			}
			
			User user = null ;
			//setEqualsObj(User.class , saveUsers,user,true,eb.getTeacherName());
			//setEqualsObj(User.class , dbUsers,user,false,eb.getTeacherName());
			for (User u : saveUsers) {
				if(eb.getTeacherName().equals(u.getRealName())){
					user = u ;
					break;
				}
			}
			if(user == null){
				for (User u : dbUsers) {
					if(eb.getTeacherName().equals(u.getRealName())){
						user = u ;
						break;
					}
				}
			}
			if(user == null){
				user = newUser(eb.getTeacherName());
				userService.register(user);
				saveUsers.add(user);
			} 
			ctt.setCourse(course);
			ctt.setShift(eb.getShift()); 
			ctt.setCourseTimes(eb.getCourseTimes());
			ctt.setUser(user);
			
			ctts.add(ctt);
		}
		
		courseTeacherTimeDao.save(ctts);
	}
	private <T>void setEqualsObj(Class<T> c, List<T> list,T obj,boolean nullable,String str){
		if(nullable == false && obj != null){
			return ;
		} 
		for (T o : list) {
			if(o.equals(str)){
				obj = o;
				return; 
			} 
		} 
	}
	
	
	private User newUser(String realName) {
		User iUser = new User();
		iUser.setRealName(realName);
		iUser.setPassWord("123");
		iUser.setUserName(realName);
		return iUser;
	}
}
