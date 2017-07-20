package com.jxufe.ctdms.enums;
 
public enum DocState {
	NOT_SUBMIT(0,"未提交"),
	WAIT_REVIEW(4,"等待审核"),
	PASS_1(7,"通过第一次审核"),
	//PASS_2(11,"通过第二次审核"),
	PASS_FINAL(19,"通过终核");
	
	private int stateId;
	private String stateMsg; 
	
	DocState(int id,String msg){
		this.stateId = id;
		this.stateMsg = msg ;
	}
	
	
	public static DocState stateOf(int index){
		for (DocState bs : values()) {
			if(bs.getStateId() == index )
				return bs;
		}
		
		return null;
	}


	public int getStateId() {
		return stateId;
	}


	public String getStateMsg() {
		return stateMsg;
	}
	
 

}
