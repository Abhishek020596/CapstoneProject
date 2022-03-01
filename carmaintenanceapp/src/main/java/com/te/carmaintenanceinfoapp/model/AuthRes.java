package com.te.carmaintenanceinfoapp.model;

public class AuthRes {
	private final String jwt;

	public AuthRes(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
}
