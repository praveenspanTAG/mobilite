package com.mobilite.spanTAG.Util;

public class ResponseInfo {
	
	public ResponseType responseType;

	public Object responseBody;

	public static enum ResponseType {
		SUCCESS, FAIL, ERROR;
	}

	public ResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public ResponseInfo() {

	}

	public ResponseInfo(ResponseType responseType, Object responseBody) {
		super();
		this.responseType = responseType;
		this.responseBody = responseBody;
	}


}
