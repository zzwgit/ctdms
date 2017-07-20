package com.jxufe.ctdms.reflect;

import java.util.ArrayList;
import java.util.List;

import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dao.CourseTeacherTimeDao;
import com.jxufe.ctdms.dto.DocDto;

public class SubmitTabTeach extends SubmitTab{

	@Override
	public List<DocDto> getDocDtos(Object obj,User user) {  
		CourseTeacherTimeDao dao = (CourseTeacherTimeDao) obj ;
		String time = "2017-04-28 23:00:00";
		List<CourseTeacherTime> ctts =  dao.findByUser(user);
		for (CourseTeacherTime ctt : ctts) {
			List<String> strs = new ArrayList<>();
			strs.add("代码:"+ctt.getCourse().getCourseCode()); 
			strs.add("班次:"+ctt.getShift());
			strs.add("开始时间:"+time);
			strs.add("结束时间:"+time); 
			DocDto dd = new DocDto (strs, ctt.getState(), ctt.getCourse().getCourseName());
			dd.setType("doc");
			docDtos.add(dd);
		}
		return docDtos; 
	} 
}
