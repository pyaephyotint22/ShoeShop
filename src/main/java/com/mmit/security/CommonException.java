package com.mmit.security;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CommonException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CommonException(String msg) {
		super(msg);
	}
}
