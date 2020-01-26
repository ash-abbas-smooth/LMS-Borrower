package com.smoothstack.avalanche.lmsborrower.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {

	/*
	 * Class that holds Api Errors
	 */
	private List<String> errors;
	
	public ApiError(List<String> error) 
	{
		this.errors = error;
	}
 }
