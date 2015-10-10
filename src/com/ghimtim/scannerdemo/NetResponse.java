package com.ghimtim.scannerdemo;

public class NetResponse {
   private int mCode;      //响应码
   private Object mMessage;   //响应详情
   public NetResponse(int code, Object message) {
	// TODO Auto-generated constructor stub
	   mCode = code;
	   mMessage = message;
}
public int getmCode() {
	return mCode;
}
public void setmCode(int mCode) {
	this.mCode = mCode;
}
public Object getmMessage() {
	return mMessage;
}
public void setmMessage(Object mMessage) {
	this.mMessage = mMessage;
}
   
}
