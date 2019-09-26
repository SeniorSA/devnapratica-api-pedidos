package br.edu.senior.devnapratica.pedidospdv.domain;

import java.io.Serializable;

public class MensagensErro implements Serializable {

	private static final long serialVersionUID = -4173796607176891668L;

	private final String[] erros;

	public MensagensErro(String... erros) {
		this.erros = erros;
	}

	@SuppressWarnings("unused")
	public String[] getErros() {
		return erros;
	}
	
}