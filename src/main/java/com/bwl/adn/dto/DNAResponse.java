package com.bwl.adn.dto;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class DNAResponse {
	private Integer status;
	private String message;
	private Object data;
	
	public DNAResponse(Integer status, String message){
		this(status,message,null);
	}
	
	public DNAResponse(Integer status, String message, @Nullable Object data){
		this.status = status;
		this.message = message;
		this.data = data;
	}
}