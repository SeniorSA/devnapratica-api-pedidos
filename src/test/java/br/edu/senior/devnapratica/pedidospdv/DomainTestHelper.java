package br.edu.senior.devnapratica.pedidospdv;

import java.time.LocalDate;

import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;

public abstract class DomainTestHelper {

	private DomainTestHelper() {
	}

	public static Cliente criarCliente(Long id, String nome, String email) {
		return criarCliente(id, nome, email, LocalDate.of(2001, 1, 1));
	}
	
	public static Cliente criarCliente(Long id, String nome, String email, LocalDate dataNascimento) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setDataNascimento(dataNascimento);
		return cliente;
	}
	
	public static Produto criarProduto(Long id, String descricao, Double valor) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setDescricao(descricao);
		produto.setValor(valor);
		return produto;
	}
	
}
