package br.edu.senior.devnapratica.pedidospdv.controllers;

import javax.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.senior.devnapratica.pedidospdv.domain.MensagensErro;
import br.edu.senior.devnapratica.pedidospdv.exception.EntidadeNaoEncontradaException;
import br.edu.senior.devnapratica.pedidospdv.exception.ErroValidacaoException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ IllegalArgumentException.class, EntidadeNaoEncontradaException.class })
	public MensagensErro tratarExcecaoCliente(Throwable throwable) {
		return new MensagensErro(throwable.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ErroValidacaoException.class })
	public MensagensErro tratarErroValidacao(ErroValidacaoException erroValidacaoException) {
		String[] mensagensValidacao = erroValidacaoException.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage).toArray(String[]::new);
		return new MensagensErro(mensagensValidacao);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public MensagensErro tratarExcecaoGenerica(Throwable throwable) {
		return new MensagensErro(throwable.getMessage());
	}

}
