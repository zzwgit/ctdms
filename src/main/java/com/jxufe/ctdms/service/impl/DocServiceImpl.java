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
			Class<?> c = Class.forName("com.jxufe.ctdms.reflect.SubmitTab"
					+ captureName(tab));
			SubmitTab submitTab = (SubmitTab) c.newInstance();
			return submitTab.getDocDtos(courseTeacherTimeDao,
					userDao.findOne(userId));
		} catch (ClassNotFoundException e) {
			return Collections.emptyList();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public List<DocDto> getWaitReviewDocByTab(String tab, long userId) {
		try {
			Class<?> c = Class.forName("com.jxufe.ctdms.reflect.SubmitTab"
					+ captureName(tab));
			SubmitTab submitTab = (SubmitTab) c.newInstance();
			submitTab.initDAO(courseDao, courseTeacherTimeDao);
			return submitTab.getReviewDocDtos(userDao.findOne(userId));
		} catch (ClassNotFoundException e) {
			return Collections.emptyList();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	// 首字母大写
	private static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	public void review(long userId, long cid, String tab, int isPass) {
		Class<?> c;
		SubmitTab submitTab = null;
		try {
			c = Class.forName("com.jxufe.ctdms.reflect.SubmitTab"
					+ captureName(tab));
			submitTab = (SubmitTab) c.newInstance();
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
		Class<?> c;
		SubmitTab submitTab = null;
		try {
			c = Class.forName("com.jxufe.ctdms.reflect.SubmitTab"
					+ captureName(doc.getTab()));
			submitTab = (SubmitTab) c.newInstance();
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

	@Override
	public void parseExcel() {
		List<ExcelBean> ebs = ExcelParse
				.parse("E:\\QQ\\QQmessage\\1059654342\\FileRecv\\软通学院本科162学期课表_撤班后.xls");
		Term term = TermServiceImpl.getNowTerm();
		// 准备单独的课程 添加到数据库
		// List<Course> singeCourses = getSingeCourseList(ebs,term);
		// System.out.println(singeCourses);
		// courseDao.save(singeCourses);
		List<Course> dbCourses = courseDao.findByTerm(term);
		List<CourseTeacherTime> ctts = new ArrayList<>();
		List<Course> saveCourses = new ArrayList<>(); // 准备保存的

		List<User> dbUsers = userDao.findAll();
		List<User> saveUsers = new ArrayList<>(); // 准备保存的

		for (ExcelBean eb : ebs) {
			CourseTeacherTime ctt = new CourseTeacherTime();
			// 检查数据库是否有相同的
			Course course = null;

			// setEqualsObj(Course.class,saveCourses,course,true,eb.getCourseCode());
			// setEqualsObj(Course.class,dbCourses,course,false,eb.getCourseCode());

			for (Course c : saveCourses) {
				if (eb.getCourseCode().equals(c.getCourseCode())) {
					course = c;
					break;
				}
			}
			if (course == null) {
				for (Course c : dbCourses) {
					if (eb.getCourseCode().equals(c.getCourseCode())) {
						course = c;
						break;
					}
				}
			}
			if (course == null) {
				course = eb.toCourse();
				course.setTerm(term);
				courseDao.save(course);
				saveCourses.add(course);
			}

			User user = null;
			// setEqualsObj(User.class ,
			// saveUsers,user,true,eb.getTeacherName());
			// setEqualsObj(User.class ,
			// dbUsers,user,false,eb.getTeacherName());
			for (User u : saveUsers) {
				if (eb.getTeacherName().equals(u.getRealName())) {
					user = u;
					break;
				}
			}
			if (user == null) {
				for (User u : dbUsers) {
					if (eb.getTeacherName().equals(u.getRealName())) {
						user = u;
						break;
					}
				}
			}
			if (user == null) {
				user = newUser(eb.getTeacherName());
				userService.register(user);
				saveUsers.add(user);
			}
			ctt.setCourse(course);
			ctt.setShift(eb.getShift());
			ctt.setCourseTimes(eb.getCourseTimes());
			ctt.setUser(user);

			ctts.add(ctt);
		}

		courseTeacherTimeDao.save(ctts);
	}

	private <T> void setEqualsObj(Class<T> c, List<T> list, T obj,
			boolean nullable, String str) {
		// TODO
		if (nullable == false && obj != null) {
			return;
		}
		for (T o : list) {
			if (o.equals(str)) {
				obj = o;
				return;
			}
		}
	}

	private User newUser(String realName) {
		User iUser = new User();
		iUser.setRealName(realName);
		iUser.setPassWord("123");
		iUser.setUserName(realName);
		return iUser;
	}

	@Override
	public String getFilePath(long docId) { 
		return  uploadRecordDao.findOne(docId).getPath();
	}
}