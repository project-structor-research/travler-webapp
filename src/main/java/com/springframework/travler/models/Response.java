package com.springframework.travler.models;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

	private Object data;
	private String massage;
	private int state;
	private Object error;
	private String result;
	
	public ResponseEntity<?> success(Object data, String msg, HttpStatus status) {
		Response response = new Response();
		response.setState(status.value());
		response.setData(data);
		response.setResult("success");
		response.setMassage(msg);
		response.setError(Collections.emptyList());		
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<?> success(String msg) {
        return success(Collections.emptyList(), msg, HttpStatus.OK);
    }
	
	public ResponseEntity<?> fail(Object data, String msg, HttpStatus status) {
		Response response = new Response();
		response.setState(status.value());
		response.setData(data);
		response.setResult("fail");
		response.setMassage(msg);
		response.setError(Collections.emptyList());
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<?> fail(String msg, HttpStatus status) {
        return fail(Collections.emptyList(), msg, status);
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
