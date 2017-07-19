package com.jxufe.ctdms.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxufe.ctdms.bean.Notices;
import com.jxufe.ctdms.bean.NoticesType;
import com.jxufe.ctdms.bean.User;
import com.jxufe.ctdms.bean.UserProfile;
import com.jxufe.ctdms.bean.UserProfileType;
import com.jxufe.ctdms.dao.NoticesDao;
import com.jxufe.ctdms.dao.NoticesTypeDao;
import com.jxufe.ctdms.dao.UserDao;
import com.jxufe.ctdms.dto.NoticesDto;
import com.jxufe.ctdms.service.NoticesService;
import com.jxufe.ctdms.utils.DateFormat;

@Service
public class NoticesServiceImpl implements NoticesService {

	private static final int NOTICES_ALL = 0;
	private static final int NOTICES_PERSON = 1;
	
	@Autowired
	NoticesTypeDao noticesTypeDao;
	@Autowired
	NoticesDao noticesDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public List<NoticesDto> getNoticetsByUserId(long userId) { 
		List<NoticesType> nts = noticesTypeDao.findAll();// 
		List<NoticesDto> nsdto = new ArrayList<>();			//返回的消息
		for (NoticesType noticesType : nts) {
			NoticesDto dto = new NoticesDto();
			dto.setTypeName(noticesType.getTypeName());
			if(noticesType.getRank() == NOTICES_ALL){	//所有人可见 
				dto.setNotices(noticesType.getNotices());
			}else if(noticesType.getRank() == NOTICES_PERSON){	//单独的消息
				List<Notices> ns = new ArrayList<>();	
				for (Notices notices : noticesType.getNotices()) {
					if(userId == notices.getUserId()){
						ns.add(notices);
					}
				}
				dto.setNotices(ns);
			}
			Collections.reverse(dto.getNotices());
			nsdto.add(dto);
		}
		return nsdto;
	}


	@Override
	public void addNewNotices(long userId,int level ,String title ,String message ,int noticesTypeId) {
		Notices notices = new Notices(); 
		
		notices.setLevel(level);
		notices.setTitle(title);
		notices.setMessage(message);
		notices.setTime(DateFormat.getFormatDate());

		User user = userDao.findByUserId(userId);
		
		notices.setAuthor(user.getRealName());	 
		NoticesType nt = noticesTypeDao.findByTypeName("最新动态");
		
		notices.setNoticesType(nt);
		System.out.println(nt);
		noticesDao.save(notices);
	}

	private int checkNoticesType(User user , int noticesTypeId){ 
		Set<UserProfile>ups = user.getUserProfiles();
		 for (UserProfile userProfile : ups) {
			 if(userProfile.getType().equals(UserProfileType.ADMIN.getUserProfileType())){
				 noticesTypeId = 0;
			 }
		}
		return noticesTypeId;
	}
}
