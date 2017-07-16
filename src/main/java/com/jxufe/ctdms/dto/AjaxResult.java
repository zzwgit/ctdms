package com.jxufe.ctdms.dto;

/**
 * 所有ajax dto 数据返回格式
 * @author Moe
 *
 * @param <T>
 */
public class AjaxResult<T> {
	private boolean success;		//是否成功

	private T data;					//数据

	private String error = "";		//失败信息
 
	public AjaxResult(T data) {
		this.success = true;
		this.data = data;
	}

	public AjaxResult(boolean suc, String error) {
		this.success = suc;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
 

}
