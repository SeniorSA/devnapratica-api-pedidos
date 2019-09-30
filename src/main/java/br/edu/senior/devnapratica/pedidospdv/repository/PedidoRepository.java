package br.edu.senior.devnapratica.pedidospdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select p from Pedido p "
			+ " inner join fetch p.cliente c "
			+ " inner join fetch p.itens itens "
			+ " inner join fetch itens.produto pr")
	List<Pedido> findAllEager();

	@Query("select p from Pedido p "
			+ " inner join fetch p.cliente c "
			+ " inner join fetch p.itens itens "
			+ " inner join fetch itens.produto pr"
			+ " where p.id = :id")
	Optional<Pedido> findByIdEager(@Param("id") Long pedidoId);
	
	
}
