package com.jxufe.ctdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.service.UserService;

/**
 * 视图控制器
 * @author Moe
 *
 */
@Controller
public class ViewController {
	@Autowired
	UserService userService;
	protected long getUserId(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userService.findByUserName(userName).getUserId();
	} 
	@RequestMapping(value = "{userId}/setting", method = RequestMethod.GET)
	public String setting() { 
		return "setting";
	}
 
	@RequestMapping(value = "{userId}/course", method = RequestMethod.GET)
	public String mycourse() { 
		return "mycourse";
	}

	@RequestMapping(value = "{userId}/mycourse", method = RequestMethod.GET)
	@ResponseBody
	public List<CourseTeacherTime> mycoursedata(@PathVariable("userId")long userId){ 
		return userService.getCTT(userId);  
	}
}
