package br.edu.senior.devnapratica.pedidospdv.util;

import java.util.Collection;

import javax.validation.ConstraintViolation;

public abstract class ValidacaoUtils {

	private ValidacaoUtils() {}

	public static void lancarErroValidacao(Collection<? extends ConstraintViolation<?>> validationMessages) {
		String mensagem = construirMensagemDeValidacao(validationMessages);
		throw new IllegalArgumentException(mensagem);
	}

	private static String construirMensagemDeValidacao(Collection<? extends ConstraintViolation<?>> validationMessages) {
		String mensagem = "";
		for(ConstraintViolation<?> mensagemValidacao : validationMessages) {
			mensagem += mensagemValidacao.getMessage();
		}
		return mensagem;
	}

}
