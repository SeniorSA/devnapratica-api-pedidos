package br.edu.senior.devnapratica.pedidospdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.senior.devnapratica.pedidospdv.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
