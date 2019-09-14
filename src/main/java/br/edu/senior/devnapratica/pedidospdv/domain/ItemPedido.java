package br.edu.senior.devnapratica.pedidospdv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ItemPedido {

	private Long id;
	
	@JsonIgnore
	private Pedido pedido;
	
	private Produto produto;
	
	private Double quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
		
}
