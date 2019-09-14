package br.edu.senior.devnapratica.pedidospdv.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import br.edu.senior.devnapratica.pedidospdv.domain.Produto;

@Component
public class ProdutoDAO {

	private static AtomicLong idSequence = new AtomicLong(0L);
	private HashMap<Long, Produto> produtosRegistrados = new LinkedHashMap<>();

	public List<Produto> buscarTodos() {
		return new LinkedList<Produto>(produtosRegistrados.values());
	}
	
	public Optional<Produto> buscar(Long produtoId) {
		return Optional.ofNullable(produtosRegistrados.get(produtoId));
	}
	
	public Produto salvar(Produto produto) {
		produto.setId(idSequence.getAndIncrement());
		produtosRegistrados.put(produto.getId(), produto);
		return produto;
	}
	
	public Produto alterar(Produto produto) {
		produtosRegistrados.put(produto.getId(), produto);
		return produto;
	}
	
	public void excluir(Long produtoId) {
		produtosRegistrados.remove(produtoId);
	}
	
}
