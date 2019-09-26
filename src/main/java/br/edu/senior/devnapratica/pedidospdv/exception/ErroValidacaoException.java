package br.edu.senior.devnapratica.pedidospdv.exception;

import java.util.Collection;

import javax.validation.ConstraintViolation;

public class ErroValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 4833716205791582911L;
	
	private final Collection<ConstraintViolation<?>> constraintViolations;
	
	public ErroValidacaoException(Collection<ConstraintViolation<?>> constraintViolations) {
		this.constraintViolations = constraintViolations;
	}

	public Collection<ConstraintViolation<?>> getConstraintViolations() {
		return constraintViolations;
	}

}
