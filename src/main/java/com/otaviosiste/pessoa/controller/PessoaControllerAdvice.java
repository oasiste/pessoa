package com.otaviosiste.pessoa.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class PessoaControllerAdvice {

	@ResponseBody
	@ExceptionHandler(PessoaNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String pessoaNotFoundHandler(PessoaNotFoundException ex) {
		return ex.getMessage();
	}


	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(
				ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
