package br.edu.senior.devnapratica.pedidospdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select count(c) from Cliente c where c.email = :email")
	long countByEmail(@Param("email") String email);

}
