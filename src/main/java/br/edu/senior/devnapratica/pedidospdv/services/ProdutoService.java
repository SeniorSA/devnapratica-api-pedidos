package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.exception.ErroValidacaoException;
import br.edu.senior.devnapratica.pedidospdv.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private Validator validator;

	public List<Produto> buscarTodos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> buscar(Long produtoId) {
		return produtoRepository.findById(produtoId);
	}

	public Produto salvar(Produto produto) {
		Set<ConstraintViolation<Produto>> validationMessages = validator.validate(produto);
		if (!validationMessages.isEmpty()) {
			throw new ErroValidacaoException(validationMessages);
		}

		return produtoRepository.save(produto);
	}

	public Produto alterar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void excluir(Long produtoId) {
		produtoRepository.deleteById(produtoId);
	}

}
