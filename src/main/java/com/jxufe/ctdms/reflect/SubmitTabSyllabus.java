package com.jxufe.ctdms.reflect;

import java.util.ArrayList;
import java.util.List;

import com.jxufe.ctdms.bean.Course;
import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.UploadRecord;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dao.CourseTeacherTimeDao;
import com.jxufe.ctdms.dto.DocDto;
import com.jxufe.ctdms.enums.DocState;

public class SubmitTabSyllabus extends SubmitTab {

	@Override
	public List<DocDto> getDocDtos(Object obj, User user) {
		CourseTeacherTimeDao dao = (CourseTeacherTimeDao) obj;
		String time = "2017-04-28 23:00:00";
		List<CourseTeacherTime> ctts = getSingeCourseTeacherTimes(dao
				.findByUser(user));
		for (CourseTeacherTime ctt : ctts) {
			List<String> strs = new ArrayList<>();
			strs.add("代码:" + ctt.getCourse().getCourseCode());
			strs.add("开始时间:" + time);
			strs.add("结束时间:" + time);
			DocDto dd = new DocDto(strs, ctt.getState(), ctt.getCourse()
					.getCourseName());
			dd.setType("doc");
			dd.setId(ctt.getCourse().getId());
			docDtos.add(dd);
		}
		return docDtos;
	}

	private List<CourseTeacherTime> getSingeCourseTeacherTimes(
			List<CourseTeacherTime> ctts) {
		List<CourseTeacherTime> c = new ArrayList<>();
		for (CourseTeacherTime ctt : ctts) {
			boolean isSinge = true;
			for (CourseTeacherTime iC : c) {
				if (iC.getCourse().getCourseCode()
						.equals(ctt.getCourse().getCourseCode())) {
					isSinge = false;
					break;
				}
			}
			if (isSinge)
				c.add(ctt);
		}
		return c;
	}

	Course course;

	@Override
	public String getFileMaskName(Object obj) {
		course = courseDao.findOne((long) obj);
		return course.getCourseName();
	}

	@Override
	public void updateDocState(UploadRecord uploadRecord) {
		course.setState(DocState.WAIT_REVIEW.getStateId());
		course.setUploadRecord(uploadRecord);
		courseDao.save(course);
	}

	@Override
	public List<DocDto> getReviewDocDtos(User user) {
		int docState = getDocStateByUserProfile(user);
		if (docState < 0) {
			return null;
		}
		List<Course> list = courseDao.findByState(docState);
		for (Course c : list) {
			List<String> strs = new ArrayList<>();
			UploadRecord ulr = c.getUploadRecord();
			strs.add(ulr.getUser().getRealName());
			strs.add(ulr.getDate());
			DocDto dd = new DocDto();
			dd.setName(c.getCourseName());
			dd.setId(c.getId());
			dd.setDocInfos(strs);
			dd.setDocId(ulr.getId());
			docDtos.add(dd);
		}
		return docDtos;
	}

	@Override
	public void review(long cid, int isPass, User user) {
		course = courseDao.findOne(cid);
		if (isPass == 0) {
			course.setState(DocState.FAIL.getStateId());
		} else if (isPass == 1) {
			if (course.getState() == DocState.WAIT_REVIEW.getStateId()) {
				course.setState(DocState.PASS_1.getStateId());
			} else if (course.getState() == DocState.PASS_1.getStateId()) {
				course.setState(DocState.PASS_FINAL.getStateId());
			}
		}
		courseDao.save(course);
	}
}
