package com.jxufe.ctdms.controller;
 
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jxufe.ctdms.bean.UploadRecord;
import com.jxufe.ctdms.service.DocService;

@Controller
public class DocController { 
	@Autowired
	DocService docService;
	@Autowired
	ServletContext context; 
	/**
	 * 获得待上传文档页面
	 */
	@RequestMapping(value = "/{userId}/submit", method = RequestMethod.GET)
	public String submit(@PathVariable("userId")long userId,
			@RequestParam(value="tab",defaultValue="teach") String tab,
			Model model) { 
		model.addAttribute("subdoc", docService.getWaitSubDocByTab(tab,userId));
		model.addAttribute("tab", tab); 
		model.addAttribute("userId",userId);
		return "submit";
	}
	/**
	 * 获得待审核文档页面
	 */
	@RequestMapping(value = "/{userId}/review", method = RequestMethod.GET)
	public String review(@PathVariable("userId")long userId,
			@RequestParam(value="tab",defaultValue="teach") String tab,
			Model model) { 
		model.addAttribute("docs", docService.getWaitReviewDocByTab(tab,userId));
		model.addAttribute("tab", tab); 
		model.addAttribute("userId",userId);
		return "review";
	}
	/**
	 * 审核操作
	 */
	@RequestMapping(value = "/{userId}/review", method = RequestMethod.POST)
	@ResponseBody
	public String doreview(@PathVariable("userId")long userId,
			@RequestParam(value = "tab",defaultValue="teach") String tab,
			@RequestParam(value = "id", required=true, defaultValue="0") long cid,
			@RequestParam(value = "isPass", required=true, defaultValue="0") int isPass) {
		docService.review(userId, cid, tab, isPass);
		return "";
	}
	/**
	 * 审核操作
	 */
	@RequestMapping(value = "/{userId}/download", method = RequestMethod.POST)
	@ResponseBody
	public String download(@PathVariable("userId")long userId,
			@RequestParam(value = "docId", required=true, defaultValue="0") long docId) {
		
		return "";
	}
	/**
	 * 上传 排课计划  cp(CoursePlan)
	 */
	@RequestMapping(value = "/cp", method = {RequestMethod.GET,RequestMethod.PUT})
	public String addCoursePlan() { 
		docService.parseExcel();
		return "";
	}

	/**
	 * 上传普通文档
	 */
	@RequestMapping(value = "upload" ,method = {RequestMethod.POST})
	@ResponseBody 
	public String addDoc( 
			@RequestParam(value = "userId" ,required=true)long userId, 
			@RequestParam(value = "file",required=true) MultipartFile file ,
			@RequestParam(value = "id", required=true, defaultValue="0") long id,
			@RequestParam(value = "tab", required=false, defaultValue="teach") String tab 
			) { 
		try{
		docService.saveDoc(userId, 
				new UploadRecord(file,tab,context.getRealPath(DocService.SAVE_FILE_PATH+DocService.UPLOAD_FILE),id)
				);
		}catch(Exception e){
			e.printStackTrace();
			return "fail";
		}
		return "";
	} 
 
	
}
