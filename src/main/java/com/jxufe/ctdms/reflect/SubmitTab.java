package com.jxufe.ctdms.reflect;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jxufe.ctdms.bean.UploadRecord;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.bean.UserProfile;
import com.jxufe.ctdms.dao.CourseDao;
import com.jxufe.ctdms.dao.CourseTeacherTimeDao;
import com.jxufe.ctdms.dto.CompletionDegreeDto;
import com.jxufe.ctdms.dto.DocDto;
import com.jxufe.ctdms.enums.DocState;
import com.jxufe.ctdms.enums.UserProfileType;

public abstract class SubmitTab {
 

	protected List<DocDto> docDtos ;
	public SubmitTab() {
		docDtos = new ArrayList<>();
	}
	public abstract List<DocDto> getDocDtos(Object obj,User user);//前台显示用

	/**
	 * 审核
	 */
	public abstract void review(long cid,int isPass,User user);//前台显示用
	
	public abstract void deleteDoc(long cid);
	
	public abstract List<CompletionDegreeDto> completes(Object userDao);//前台显示用
	/**
	 * 根据用户权限 显示相应的 Doc
	 * @param user
	 * @return
	 */
	public abstract List<DocDto> getReviewDocDtos(User user);//前台显示用
	
	protected int getDocStateByUserProfile(User user){
		int rank = getReviewRankByUser(user);
		int docState = -1 ;
		if(rank == UserProfileType.DIRECTOR.getProfileTypeId()){
			docState = DocState.WAIT_REVIEW.getStateId();
		} else if(rank == UserProfileType.DEAN.getProfileTypeId()){
			docState = DocState.PASS_1.getStateId();
		}
		return docState;
	}
	
	private int getReviewRankByUser(User user){
		int rank = 0 ;
		Set<UserProfile>ups = user.getUserProfiles();
		for (UserProfile userProfile : ups) {
			if(rank < userProfile.getId()){
				rank = userProfile.getId();	//得到最大rank值	0 无权限
			}
		}
		return rank;
	}
	
	public abstract String getFileMaskName(Object obj);	//文档名
	
	public abstract void updateDocState(UploadRecord uploadRecord);			//更新文档状态
	CourseTeacherTimeDao courseTeacherTimeDao;
	CourseDao courseDao;
	
	public void initDAO(CourseDao d1,CourseTeacherTimeDao d2){
		courseDao = d1;
		courseTeacherTimeDao = d2;
	} 
	
	
	public static SubmitTab getInstanceByTab(String tab) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class<?> c = Class.forName("com.jxufe.ctdms.reflect.SubmitTab"
				+ captureName(tab)); 
		return  (SubmitTab) c.newInstance();
	}
	

	// 首字母大写
	private static String captureName(String name) { 
		char[] cs = name.toCharArray(); 
		cs[0] -= 32;
		return String.valueOf(cs);
	}
}
