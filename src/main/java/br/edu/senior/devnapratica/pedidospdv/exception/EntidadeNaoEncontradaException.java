package br.edu.senior.devnapratica.pedidospdv.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 4182171978445848773L;

	public EntidadeNaoEncontradaException(Class<?> classe, Object id) {
		super("Nenhuma entidade do tipo " + classe.getSimpleName() +
				" encontrada para a id '" + id + "'.");
	}
	
}
