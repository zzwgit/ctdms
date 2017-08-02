package com.jxufe.ctdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.LimitDate;
import com.jxufe.ctdms.dto.AjaxResult;
import com.jxufe.ctdms.service.SettingService;
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
	@Autowired
	SettingService settingService;
	
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
	/**
	 * 获得设置界面
	 */
	@RequestMapping(value = "{userId}/setting", method = RequestMethod.GET)
	public String setting() { 
		return "setting";
	}
	/**
	 * 获得限制日期界面
	 */
	@RequestMapping(value = "{userId}/limit", method = RequestMethod.GET)
	public String limit() { 
		return "limit";
	}	    	
	/**
	 * 	设置 限制日期  
	 *  tab : type,		文档类型
	 * 	start : _start,	开始时间
	 *	end : _end		结束时间
	 */ 
	@RequestMapping(value = "{userId}/limit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult<String> Plimit(
			@RequestParam(value="tab",required=true) String tab, 
			@RequestParam(value="start",required=true) String start, 
			@RequestParam(value="end",required=true) String end
			) {  
		settingService.setLimitDate(new LimitDate(start,end,tab));
		return new AjaxResult<>("success");
	}
	/**
	 * 获得课程表界面
	 */
	@RequestMapping(value = "{userId}/course", method = RequestMethod.GET)
	public String mycourse() { 
		return "mycourse";
	}
	/**
	 * 获得课程数据
	 */
	@RequestMapping(value = "{userId}/mycourse", method = RequestMethod.GET)
	@ResponseBody
	public List<CourseTeacherTime> mycoursedata(@PathVariable("userId")long userId){ 
		return userService.getCTT(userId);  
	}
	/**
	 * 获得帮助界面
	 */
	@RequestMapping(value = "{userId}/help", method = RequestMethod.GET)
	public String help() { 
		return "help";
	} 
	/**
	 * 获得个人信息界面
	 */
	@RequestMapping(value = "{userId}/info", method = RequestMethod.GET)
	public String info() { 
		return "info";
	} 
	/**
	 * 获得申请教材界面
	 */
	@RequestMapping(value = "{userId}/declaration", method = RequestMethod.GET)
	public String declaration() { 
		return "info";
	} 	
	/**
	 * 获得统计图表界面
	 */
	@RequestMapping(value = "{userId}/statistics", method = RequestMethod.GET)
	public String statistics() { 
		return "info";
	} 
	/**
	 * 获得我的文档界面
	 */
	@RequestMapping(value = "{userId}/docs", method = RequestMethod.GET)
	public String docs() { 
		return "info";
	} 
	/**
	 * 获得版本更新界面
	 */
	@RequestMapping(value = "{userId}/update", method = RequestMethod.GET)
	public String update() { 
		return "info";
	} 
	/**
	 * 获得反馈界面
	 */
	@RequestMapping(value = "{userId}/feedback", method = RequestMethod.GET)
	public String feedback() { 
		return "info";
	} 
}
