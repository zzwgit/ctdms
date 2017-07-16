package com.jxufe.ctdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxufe.ctdms.dto.AjaxResult;
import com.jxufe.ctdms.dto.NoticesDto;
import com.jxufe.ctdms.service.NoticesService;

@Controller
public class NoticesController {
	@Autowired
	NoticesService noticesService; 
	
	@RequestMapping(value="notices", method = RequestMethod.GET) 
	public String getNoticesFrame(){  
		return "notices";
	 } 
	
	@RequestMapping(value="{userId}/notices", method = RequestMethod.POST) 
	@ResponseBody
	public AjaxResult<List<NoticesDto>> getNotices(@PathVariable("userId") long userId){  
		try{
			List<NoticesDto> dtos = noticesService.getNoticetsByUserId(userId);
			System.out.println(dtos);
			return new AjaxResult<>(dtos);
		}catch(Exception e){ 
			return new AjaxResult<>(false,"未知异常");
		} 
	 } 

	@RequestMapping(value="{userId}/notices", method = RequestMethod.PUT) 
	@ResponseBody
	public AjaxResult<String> addNotices(@PathVariable("userId") long userId,
			@RequestParam(value = "level", required = true) int level,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "noticesType", required = true) String noticesType){  
		try{  
			noticesService.addNewNotices(userId, level ,title ,message , noticesType);
			return new AjaxResult<>("");
		}catch(Exception e){ 
			return new AjaxResult<>(false,"未知异常");
		}
		
	 } 
}
