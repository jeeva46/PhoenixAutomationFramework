package com.api.constant;

public enum Warranty {
	IN_WARRANTY(1), OUT_WARRANTY(2);
	
	private int code;
	
	private Warranty(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
