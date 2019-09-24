package br.edu.senior.devnapratica.pedidospdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.services.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/v1/produtos")
	public ResponseEntity<List<Produto>> listar() {
		List<Produto> produtos = produtoService.buscarTodos();
		return ResponseEntity.ok(produtos);
	}

	@PostMapping("/v1/produtos")
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		Produto produtoSalvo = produtoService.salvar(produto);
		return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
	}

	@GetMapping("/v1/produtos/{produtoId}")
	public ResponseEntity<Produto> buscar(Long produtoId) {
		Optional<Produto> produtoOpt = produtoService.buscar(produtoId);

		if(produtoOpt.isPresent()) {
			return ResponseEntity.ok(produtoOpt.get());
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/v1/produtos/{produto.id}")
	public ResponseEntity<Produto> alterar(@RequestBody Produto produto) {
		Produto produtoAlterado = produtoService.alterar(produto);
		return new ResponseEntity<Produto>(produtoAlterado, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/v1/produtos/{produtoId}")
	public void remover(@PathVariable("produtoId") Long produtoId) {
		produtoService.excluir(produtoId);
	}
	
}
