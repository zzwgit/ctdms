package com.jxufe.ctdms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jxufe.ctdms.bean.UploadRecord;
import com.jxufe.ctdms.dto.CompletionDegreeDto;
import com.jxufe.ctdms.dto.DocDto;

public interface DocService {

	public static String SAVE_FILE_PATH = "/WEB-INF/doc/";  
	public static String UPLOAD_FILE = "upload/"; 
	public static String PDF_FILE = "pdf/"; 
	
	void parseLocalExcel();
	
	List<DocDto> getWaitSubDocByTab(String tab,long userId);
	
	List<DocDto> getWaitReviewDocByTab(String tab,long userId);
	
	public void review(long userId ,long cid , String tab ,int isPass);
	public List<CompletionDegreeDto> getCompletionDegrees(String tab);
	
	
	public void saveDoc(long userId , UploadRecord doc ) throws IOException;
	public String getFilePath(long docId);

	void delete(long userId,String tab, long cid);

	void cp(MultipartFile file)  throws IOException;
}
