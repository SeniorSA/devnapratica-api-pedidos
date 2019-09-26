package br.edu.senior.devnapratica.pedidospdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
}
