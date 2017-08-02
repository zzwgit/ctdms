package com.jxufe.ctdms.reflect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jxufe.ctdms.bean.Course;
import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.LimitDate;
import com.jxufe.ctdms.bean.UploadRecord;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dao.LimitDateDao;
import com.jxufe.ctdms.dto.CompletionDegreeDto;
import com.jxufe.ctdms.dto.DocDto;
import com.jxufe.ctdms.dto.Schedule;
import com.jxufe.ctdms.enums.DocState;
import com.jxufe.ctdms.service.impl.TermServiceImpl;
import com.jxufe.ctdms.utils.DateFormat;

public class SubmitTabTeach extends SubmitTab {

	@Override
	public List<DocDto> getDocDtos(Object obj, User user) {
		LimitDateDao dao = (LimitDateDao) obj;
		long termId = TermServiceImpl.getNowTerm().getTermId();
		LimitDate limit = dao.findByTabAndIsWorkAndTerm("all", 1 , termId);
		if(limit==null){
			limit = dao.findByTabAndIsWorkAndTerm("teach", 1 , termId);
		}
		String start,end ,isTimeUp = "no";
		if(limit==null){
			start = end = "无时间限制";
		}else{
			start = limit.getStart() +" 00:00:00";
			end   = limit.getEnd() +" 00:00:00";
			long now = System.currentTimeMillis();
			if(DateFormat.timeStringToMillisSec(start ) > now ||
					now > DateFormat.timeStringToMillisSec(end )){
						isTimeUp = "yes";
					}
		}
		List<CourseTeacherTime> ctts = courseTeacherTimeDao.findByUser(user);
		for (CourseTeacherTime ctt : ctts) {
			List<String> strs = new ArrayList<>();
			strs.add("代码: " + ctt.getCourse().getCourseCode());
			strs.add("班次: " + ctt.getShift());
			strs.add("开始时间: " + start);
			strs.add("结束时间: " + end);
			DocDto dd = new DocDto(strs, ctt.getState(), ctt.getCourse()
					.getCourseName());
			dd.setType("doc");
			dd.setId(ctt.getId());
			dd.setIsTimeUp(isTimeUp);
			docDtos.add(dd);
		}
		return docDtos;
	}

	/**
	 * <th>课程</th> <th>班次</th> <th>上传者</th> <th>最后一次修改时间</th>
	 */
	@Override
	public List<DocDto> getReviewDocDtos(User user) {
		int docState = getDocStateByUserProfile(user);
		if (docState < 0) {
			return null;
		}
		List<CourseTeacherTime> list = courseTeacherTimeDao
				.findByState(docState);
		for (CourseTeacherTime c : list) {
			List<String> strs = new ArrayList<>();
			UploadRecord ulr = c.getUploadRecord();
			strs.add(c.getShift());
			strs.add(ulr.getUser().getRealName());
			 
			DocDto dd = new DocDto();
			dd.setName(c.getCourse().getCourseName());
			dd.setId(c.getId());
			dd.setDocInfos(strs);
			dd.setDate(ulr.getDate());
			dd.setDocId(ulr.getId());
			docDtos.add(dd);
		}
		Collections.sort(docDtos, new Comparator<DocDto>() {
	            public int compare(DocDto o1, DocDto o2) {
	                return o2.getDate().compareTo(o1.getDate());
	            }
	    });
		return docDtos;
	}

	CourseTeacherTime courseTeacherTime;

	@Override
	public String getFileMaskName(Object obj) {
		courseTeacherTime = courseTeacherTimeDao.findOne((long) obj);
		return courseTeacherTime.getCourse().getCourseName();
	}

	@Override
	public void updateDocState(UploadRecord uploadRecord) {
		courseTeacherTime.setState(DocState.WAIT_REVIEW.getStateId());
		courseTeacherTime.setUploadRecord(uploadRecord);
		courseTeacherTimeDao.save(courseTeacherTime);
	}

	@Override
	public void review(long cid, int isPass, User user) {
		courseTeacherTime = courseTeacherTimeDao.findOne(cid);
		if (isPass == 0) {
			courseTeacherTime.setState(DocState.FAIL.getStateId());
		} else if (isPass == 1) {
			if (courseTeacherTime.getState() == DocState.WAIT_REVIEW
					.getStateId()) {
				courseTeacherTime.setState(DocState.PASS_1.getStateId());
			} else if (courseTeacherTime.getState() == DocState.PASS_1
					.getStateId()) {
				courseTeacherTime.setState(DocState.PASS_FINAL.getStateId());
			}
		}
		courseTeacherTimeDao.save(courseTeacherTime);
	}

	@Override
	public List<CompletionDegreeDto> completes(Object userDao) {
		List<Course> course = courseDao.findAll();
		List<CompletionDegreeDto> cdds = new ArrayList<>();
		for (Course c : course) {
			List<CourseTeacherTime> ctts = courseTeacherTimeDao.findByCourse(c);
			CompletionDegreeDto cdd = new CompletionDegreeDto();
			List<Schedule> schedules = new ArrayList<>();
			for (CourseTeacherTime ctt : ctts) {
				Schedule s = new Schedule();
				s.setShift(ctt.getShift());
				s.setTeacherName(ctt.getUser().getRealName());
				s.setState(ctt.getState()); 
				schedules.add(s);
			} 
			cdd.setCode(c.getCourseCode());
			cdd.setName(c.getCourseName());
			cdd.setState(c.getState());
			cdd.setSchedules(schedules);
			cdds.add(cdd);
		} 
		return cdds;
	}

	@Override
	public void deleteDoc(long cid) {
		courseTeacherTime = courseTeacherTimeDao.findOne(cid);  
		courseTeacherTime.setState(DocState.NOT_SUBMIT.getStateId());
		courseTeacherTimeDao.save(courseTeacherTime);
	}

}
