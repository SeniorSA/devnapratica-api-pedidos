package br.edu.senior.devnapratica.pedidospdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.edu.senior.devnapratica.pedidospdv.dao.ProdutoDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.util.ValidacaoUtils;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private Validator validator;

	public List<Produto> buscarTodos() {
		return produtoDAO.buscarTodos();
	}

	public Optional<Produto> buscar(Long produtoId) {
		return produtoDAO.buscar(produtoId);
	}

	public Produto salvar(Produto produto) {
		Set<ConstraintViolation<Produto>> validationMessages = validator.validate(produto);
		if (!validationMessages.isEmpty()) {
			ValidacaoUtils.lancarErroValidacao(validationMessages);
		}

		return produtoDAO.salvar(produto);
	}

	public Produto alterar(Produto produto) {
		return produtoDAO.alterar(produto);
	}

	public void excluir(Long produtoId) {
		produtoDAO.excluir(produtoId);
	}

}
