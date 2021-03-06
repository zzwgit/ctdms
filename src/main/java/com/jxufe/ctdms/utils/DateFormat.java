package com.jxufe.ctdms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormat {
	
	public static String getFormatDate(){
		return timeMillisToString(System.currentTimeMillis());
	}
	/**
	 * (毫秒)转为日期
	 * @param millis
	 * @return
	 */
	public static String timeMillisToString(long millis){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  dateformat.format(millis);
	}
	/**
	 * 日期转为(秒)
	 * @param str
	 * @return
	 */  
	public static long timeStringToMillisSec(String str){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		    long time = dateformat.parse(str).getTime(); 
			return time;
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		System.out.println(timeStringToMillisSec("2017-08-24 00:00:00"));
	}
}
