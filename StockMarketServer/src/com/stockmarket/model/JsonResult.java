package com.stockmarket.model;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonResult() {
		code = 10000;
		msg = "服务器处理异常！";
		data = null;
	}

	int code;
	String msg;
	T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T value) {
		this.data = value;
	}

}
