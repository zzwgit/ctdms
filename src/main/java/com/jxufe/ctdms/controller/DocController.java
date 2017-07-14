package com.jxufe.ctdms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jxufe.ctdms.utils.Zip;

@Controller
public class DocController {
	private static Logger log = LogManager.getLogger(DocController.class
			.getName()); 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return "index";
	}
 
	/**
	 * 删除文档
	 */
	@RequestMapping(value = "doc", method = RequestMethod.DELETE)
	public String deleteDoc() { 
		return "";
	}
	/**
	 * 上传 排课计划  cp(CoursePlan)
	 */
	@RequestMapping(value = "cp", method = RequestMethod.PUT)
	public String addCoursePlan() {
		saveUploadFile();
		return "";
	}
	/**
	 * 上传普通文档
	 */
	@RequestMapping(value = "doc", method = RequestMethod.PUT)
	public String addDoc() {
		
		saveUploadFile();
		return "";
	}
	/**
	 * 压缩后保存上传文档
	 */
	private void saveUploadFile(){
		new Zip().compression();
		saveZipToLocal();
	}
	private void saveZipToLocal(){
		
	}
	/**
	 * 下载文档
	 */
	@RequestMapping(value = "doc", method = RequestMethod.GET)
	public String downloadDoc() {
		getLocalFilePath();
		getLocalFileByPath();
		return "";
	}

	/**
	 * 获得本地文件路径
	 */
	private void getLocalFilePath() { 
		
	}
	/**
	 * 解压后返回给客户端
	 */
	private void getLocalFileByPath() { 
		new Zip().decompression();
	}
	
	
}
