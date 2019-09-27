package br.edu.senior.devnapratica.pedidospdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select p from Pedido p "
			+ " inner join fetch p.cliente c "
			+ " inner join fetch p.itens itens "
			+ " inner join fetch itens.produto pr")
	List<Pedido> findAllEager();
	
	
}
