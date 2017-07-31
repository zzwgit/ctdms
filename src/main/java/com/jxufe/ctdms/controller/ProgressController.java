package com.jxufe.ctdms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jxufe.ctdms.utils.Progress;
 
/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2013-12-6 <br>
 * 功能描述： 获取上传文件进度controller<br>
 *
 */
@Controller 
public class ProgressController{

	@RequestMapping(value = "{userId}/progress", headers = "Accept=application/json", method = {RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public Progress initCreateInfo(HttpSession session,
			@PathVariable("userId")long userId) {
		
		Progress status = (Progress) session.getAttribute("upload_ps");
		if(status==null){
			return null;
		}   
		return status;
	}
}