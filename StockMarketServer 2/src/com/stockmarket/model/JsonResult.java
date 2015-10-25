package com.stockmarket.model;

import java.io.Serializable;

public class JsonResult implements Serializable {
	public JsonResult() {
		code = 10000;
		msg = "服务器处理异常！";
		data = null;
	}

	int code;
	String msg;
	Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object value) {
		this.data	 = value;
	}

}
