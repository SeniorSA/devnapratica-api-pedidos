package br.edu.senior.devnapratica.pedidospdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	
}
