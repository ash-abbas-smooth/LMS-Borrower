package com.smoothstack.avalanche.lmsborrower.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javassist.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ApiError> handleIllegaArgumentException(IllegalArgumentException ex) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
		List<String> errors = Collections.singletonList(ex.getMessage());
		ApiError body = new ApiError(errors);
		return new ResponseEntity<ApiError>(body, headers, status );
	}

	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NOT_FOUND;
		List<String> errors = Collections.singletonList(ex.getMessage());
		ApiError body = new ApiError(errors);
		return new ResponseEntity<ApiError>(body, headers, status );
	}

}
