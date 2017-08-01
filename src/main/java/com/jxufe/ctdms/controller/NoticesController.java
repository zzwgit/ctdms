package com.jxufe.ctdms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jxufe.ctdms.dto.NoticesDto;
import com.jxufe.ctdms.service.NoticesService;

@Controller
public class NoticesController {
	@Autowired
	NoticesService noticesService; 
	
	/**
	 * 获得发布通知界面
	 */
	@RequestMapping(value="{userId}/Announcement", method = RequestMethod.GET) 
	public String deliverNotice(@PathVariable("userId") long userId,Model model){
		model.addAttribute("userId", userId);
		return "delivernotice";
	} 
	
	/**
	 * 显示所有通知
	 */
	@RequestMapping(value="{userId}/notices", method = RequestMethod.GET) 
	public String getNoticesFrame(@PathVariable("userId") long userId,
			Model model){  
		List<NoticesDto> dtos = noticesService.getNoticetsByUserId(userId);
		model.addAttribute("noticesdto", dtos); 
		return "notices";
	} 
	
	/**
	 * 发布通知
	 */
	@RequestMapping(value="{userId}/notices", method = RequestMethod.POST) 
	public String addNotices(@PathVariable("userId") long userId,
			@RequestParam(value = "level", required = false,defaultValue="0") int level,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "noticesTypeId", required = false , defaultValue="1") int noticesTypeId,
			Model model){
			try{
				noticesService.addNewNotices(userId, level ,title ,message , noticesTypeId);
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("fail", "1");
			}
			model.addAttribute("userId", userId);
			return "redirect:notices";
			//delivernotice;
	 } 
}
