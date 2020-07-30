package com.wang.util;

public enum ReturnResult {
	
	SUCCESS(1,"成功"),
	ERROR(0,"失败"),
	REPEATERROR(-1,"重复出现");
	
	private Integer code;
	private String message;
	
	ReturnResult(Integer code,String message){
		this.code=code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 
}
