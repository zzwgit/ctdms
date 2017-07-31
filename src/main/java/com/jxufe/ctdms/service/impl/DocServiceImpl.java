package com.jxufe.ctdms.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jxufe.ctdms.bean.Course;
import com.jxufe.ctdms.bean.CourseTeacherTime;
import com.jxufe.ctdms.bean.Term;
import com.jxufe.ctdms.bean.UploadRecord;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.dao.CourseDao;
import com.jxufe.ctdms.dao.CourseTeacherTimeDao;
import com.jxufe.ctdms.dao.DocTypeDao;
import com.jxufe.ctdms.dao.UploadRecordDao;
import com.jxufe.ctdms.dao.UserDao;
import com.jxufe.ctdms.dto.CompletionDegreeDto;
import com.jxufe.ctdms.dto.DocDto;
import com.jxufe.ctdms.reflect.SubmitTab;
import com.jxufe.ctdms.service.DocService;
import com.jxufe.ctdms.service.UserService;
import com.jxufe.ctdms.utils.DateFormat;
import com.jxufe.ctdms.utils.ExcelBean;
import com.jxufe.ctdms.utils.ExcelParse;
import com.jxufe.ctdms.utils.Zip;

@Service
public class DocServiceImpl implements DocService {

	@Autowired
	UserService userService;
	@Autowired
	CourseDao courseDao;
	@Autowired
	UserDao userDao;
	@Autowired
	CourseTeacherTimeDao courseTeacherTimeDao;
	@Autowired
	UploadRecordDao uploadRecordDao;
	@Autowired
	DocTypeDao docTypeDao;

	@Override
	public List<DocDto> getWaitSubDocByTab(String tab, long userId) {
		try {
			SubmitTab submitTab = SubmitTab.getInstanceByTab(tab); 
			return submitTab.getDocDtos(courseTeacherTimeDao,
					userDao.findOne(userId));
		} catch (ClassNotFoundException e) {
			return Collections.emptyList();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public List<DocDto> getWaitReviewDocByTab(String tab, long userId) {
		try { 
			SubmitTab submitTab = SubmitTab.getInstanceByTab(tab); 
			submitTab.initDAO(courseDao, courseTeacherTimeDao);
			return submitTab.getReviewDocDtos(userDao.findOne(userId));
		} catch (ClassNotFoundException e) {
			return Collections.emptyList();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	} 
	public void review(long userId, long cid, String tab, int isPass) { 
		SubmitTab submitTab = null;
		try {
			submitTab = SubmitTab.getInstanceByTab(tab); 
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
			return;
		}
		// 反射得到的 class 初始化
		submitTab.initDAO(courseDao, courseTeacherTimeDao);
		submitTab.review(cid, isPass, userDao.findOne(userId));
	}

	public void saveDoc(long userId, UploadRecord doc) throws IOException { 
		SubmitTab submitTab = null;
		try {
			submitTab = SubmitTab.getInstanceByTab(doc.getTab()); 
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
			return;
		}
		// 反射得到的 class 初始化
		submitTab.initDAO(courseDao, courseTeacherTimeDao);

		MultipartFile multipartFile = doc.getFile();
		doc.setName(multipartFile.getOriginalFilename());
		// 设置路径并新建文件夹
		String path = doc.getPath() + "/学期-"
				+ TermServiceImpl.getNowTerm().getTermName() + "/";
		path += submitTab.getFileMaskName(doc.getCid());
		mkdirs(path);

		doc.setSize(multipartFile.getSize());
		doc.setPath(path + "/" + doc.getName());

		saveUploadFile(multipartFile.getBytes(), new File(doc.getPath()));
		System.out.println("[文件上传] " + doc.getName() + "-保存至服务器 - 数据库同步中...");
		// 保存成功 更新课程 -- 状态

		// 保存上传 记录
		User user = userDao.findOne(userId);
		doc.setUser(user);
		doc.setTerm(TermServiceImpl.getNowTerm());
		doc.setDocType(docTypeDao.findByTabName(doc.getTab()));
		// 2015-04-03 12:12:12
		doc.setDate(DateFormat.getFormatDate());
		uploadRecordDao.save(doc);
		submitTab.updateDocState(doc);
	}

	/**
	 * 压缩后保存上传文档
	 * 
	 * @throws IOException
	 */
	private void saveUploadFile(byte[] bytes, File outfile) throws IOException {
		new Zip().compression(bytes);
		FileCopyUtils.copy(bytes, outfile);
	}

	/**
	 * 获得本地文件路径
	 */
	private void getLocalFilePath() {
		// TODO
	}

	/**
	 * 解压后返回给客户端
	 */
	private void getLocalFileByPath() {
		// TODO
		new Zip().decompression();
	}

	public static void mkdirs(String filePath) {
		// 确保文件夹存在
		synchronized (filePath) {
			File directory = new File(filePath);
			if (!directory.exists()) {// 如果文件夹结构不存在
				directory.mkdirs(); // 创建文件夹结构
				System.out.println(directory.getAbsolutePath());
			}
		}
	}
	/**
	 * TODO 重构.
	 */
	/**
	 * 1.检查当前学期是否已上传  
	 * 2.已上传则先删除本学期 所有课程, 未上传进入下一步
	 * 3.导入课程,教师
	 */
	@Override
	public void parseExcel() {
		List<ExcelBean> ebs = ExcelParse
				.parse("E:\\QQ\\QQmessage\\1059654342\\FileRecv\\软通学院本科162学期课表_撤班后.xls");
		Term term = TermServiceImpl.getNowTerm();

		List<Course> dbCourses = courseDao.findByTerm(term);
		List<CourseTeacherTime> ctts = new ArrayList<>();
		List<Course> saveCourses = new ArrayList<>(); // 准备保存的

		List<User> dbUsers = userDao.findAll();
		List<User> saveUsers = new ArrayList<>(); // 准备保存的

		for (ExcelBean eb : ebs) {
			CourseTeacherTime ctt = new CourseTeacherTime(); 
			User user = (User) getEobj(eb.getTeacherName(),saveUsers,dbUsers,User.class);
 
			if (user == null) { 
				user = new User();
				user.setRealName(eb.getTeacherName());
				user.setPassWord("123");
				user.setUserName(eb.getTeacherName());
				saveUsers.add(user);
			} 

			Course course = (Course) getEobj(eb.getCourseCode(),saveCourses,dbCourses,Course.class);
 
			if (course == null) {
				course = eb.toCourse();
				course.setTerm(term);
				saveCourses.add(course);
			}
			 
			ctt.setCourse(course);
			ctt.setShift(eb.getShift());
			ctt.setCourseTimes(eb.getCourseTimes());
			ctt.setUser(user);

			ctts.add(ctt);
		}
		 
		courseDao.save(saveCourses); 
		userService.registerTearchers(saveUsers);
		courseTeacherTimeDao.save(ctts);
	}

	private <T> Object getEobj(String s, List<T> objs1,List<T>objs2,Class<T>c) { 
		for (Object o : objs1) {
			if(o.equals(s)){
				return o ; 
			}
		}
		for (Object o : objs2) {
			if(o.equals(s)){
				return o ; 
			}
		}
		return null;
	}
 
	@Override
	public String getFilePath(long docId) { 
		return  uploadRecordDao.findOne(docId).getPath();
	}

	@Override
	public List<CompletionDegreeDto> getCompletionDegrees(String tab) {
		try {
			SubmitTab submitTab = SubmitTab.getInstanceByTab(tab);
			submitTab.initDAO(courseDao, courseTeacherTimeDao);
			return submitTab.completes(userDao);
		} catch (InstantiationException | IllegalAccessException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {  
			return Collections.emptyList();
		}
		return Collections.emptyList(); 
	}

	@Override
	public void delete(long userId,String tab, long cid) {
		try {
			SubmitTab submitTab = SubmitTab.getInstanceByTab(tab);
			submitTab.initDAO(courseDao, courseTeacherTimeDao); 
			submitTab.deleteDoc(cid);
		} catch (InstantiationException | IllegalAccessException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) {   
		}
	} 
}