package com.jxufe.ctdms.reflect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jxufe.ctdms.bean.Course;
import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.UploadRecord;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dao.CourseTeacherTimeDao;
import com.jxufe.ctdms.dto.CompletionDegreeDto;
import com.jxufe.ctdms.dto.DocDto;
import com.jxufe.ctdms.dto.Schedule;
import com.jxufe.ctdms.enums.DocState;

public class SubmitTabTeach extends SubmitTab {

	@Override
	public List<DocDto> getDocDtos(Object obj, User user) {
		CourseTeacherTimeDao dao = (CourseTeacherTimeDao) obj;
		String time = "2017-04-28 23:00:00";
		List<CourseTeacherTime> ctts = dao.findByUser(user);
		for (CourseTeacherTime ctt : ctts) {
			List<String> strs = new ArrayList<>();
			strs.add("代码: " + ctt.getCourse().getCourseCode());
			strs.add("班次: " + ctt.getShift());
			strs.add("开始时间: " + time);
			strs.add("结束时间: " + time);
			DocDto dd = new DocDto(strs, ctt.getState(), ctt.getCourse()
					.getCourseName());
			dd.setType("doc");
			dd.setId(ctt.getId());
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
