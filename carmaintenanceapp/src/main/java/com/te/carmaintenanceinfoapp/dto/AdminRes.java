package com.te.carmaintenanceinfoapp.dto;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.Data;

@Data
@JsonIncludeProperties
public class AdminRes {

	private boolean error;
	private String message;
	private String token;

	public AdminRes(boolean error, String message, String token) {
		this.error = error;
		this.message = message;
		this.token = token;
	}
}
