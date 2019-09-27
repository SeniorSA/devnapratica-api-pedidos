package br.edu.senior.devnapratica.pedidospdv.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.senior.devnapratica.pedidospdv.domain.Produto;

public class ProdutoDAOTest {

	@Test
	public void deveSalvarProduto() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produtoSalvo = produtoDAO.salvar(new Produto());
		assertNotNull(produtoSalvo.getId());
		assertEquals(1, produtoDAO.buscarTodos().size());
	}

	@Test
	public void deveSalvarVariosProdutos() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(new Produto());
		produtoDAO.salvar(new Produto());
		assertEquals(2, produtoDAO.buscarTodos().size());
	}
	
}
