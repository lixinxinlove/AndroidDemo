package com.lee.androiddemo.http;

import okhttp3.Call;

public abstract class RequestCallback {

	private Call mCall;
	/**
	 * 请求结果回调
	 * 
	 * @param res
	 */
	public abstract void RequestCallback(ResponseEntity res);

	/**
	 * 请求取消
	 */
	public void onCancelled() {
	}

	/**
	 * 请求加载中
	 * 
	 * @param total
	 * @param current
	 * @param isUploading
	 */
	public void onLoading(long total, long current, boolean isUploading) {

	}

	/**
	 * 请求开始
	 */
	public void onStart() {

	}

	public Call getmCall() {
		return mCall;
	}

	public void setmCall(Call mCall) {
		this.mCall = mCall;
	}
}
