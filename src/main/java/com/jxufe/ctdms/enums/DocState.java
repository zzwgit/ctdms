package com.jxufe.ctdms.enums;
 
public enum DocState {
	NOT_SUBMIT(0,"未提交","no-submit"),
	WAIT_REVIEW(4,"待审核","wait"),
	PASS_1(7,"待终审","pass-1"),
	//PASS_2(11,"通过第二次审核"),
	PASS_FINAL(19,"已通过","pass-2"),
	FAIL(-2,"失败","fail");
	
	private int stateId;
	private String stateMsg; 
	private String plusMsg; 
	
	DocState(int id,String msg,String plusMsg){
		this.stateId = id;
		this.stateMsg = msg ;
		this.plusMsg=plusMsg;
	}
	
	
	public static DocState stateOf(int index){
		for (DocState bs : values()) {
			if(bs.getStateId() == index )
				return bs;
		}
		
		return null;
	}


	public String getPlusMsg() {
		return plusMsg;
	}


	public int getStateId() {
		return stateId;
	}


	public String getStateMsg() {
		return stateMsg;
	}
	
 

}
