package com.jxufe.ctdms.reflect;

import java.util.ArrayList;
import java.util.List;

import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dto.DocDto;

public abstract class SubmitTab {
 

	protected List<DocDto> docDtos ;
	public SubmitTab() {
		docDtos = new ArrayList<>();
	}
	public abstract List<DocDto> getDocDtos(Object obj,User user);
	
//	public List<Course> teach(){
//		System.out.println("teach");
//		return null;
//		
//	}
//	public List<Course> syllabus(){
//		System.out.println("syllabus");
//		return null;
//		
//	} 
//	public List<Course> practice(){
//		System.out.println("practice");
//		return null;
//		
//	}	
//	public List<Course> other(){
//		System.out.println("other");
//		return null;
//		
//	}
}
