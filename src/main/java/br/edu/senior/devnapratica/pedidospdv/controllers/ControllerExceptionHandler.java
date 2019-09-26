package br.edu.senior.devnapratica.pedidospdv.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.senior.devnapratica.pedidospdv.exception.EntidadeNaoEncontradaException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		IllegalArgumentException.class,
		EntidadeNaoEncontradaException.class
	})
	public String tratarExcecao(Exception illegalArgumentException) {
		return illegalArgumentException.getMessage();
	}
	
}
