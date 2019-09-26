package br.edu.senior.devnapratica.pedidospdv.dao.controllers;

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

import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;
import br.edu.senior.devnapratica.pedidospdv.dao.services.PedidoServiceDAO;

@Deprecated
@RestController
public class PedidoControllerDAO {

	@Autowired
	private PedidoServiceDAO pedidoService;

	@GetMapping("/v0/pedidos")
	public ResponseEntity<List<Pedido>> listar() {
		List<Pedido> pedidos = pedidoService.buscarTodos();
		return ResponseEntity.ok(pedidos);
	}

	@PostMapping("/v0/pedidos")
	public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
		Pedido pedidoSalvo = pedidoService.salvar(pedido);
		return new ResponseEntity<>(pedidoSalvo, HttpStatus.CREATED);
	}

	@GetMapping("/v0/pedidos/{pedidoId}")
	public ResponseEntity<Pedido> buscar(Long pedidoId) {
		Optional<Pedido> pedidoOpt = pedidoService.buscar(pedidoId);

		if (pedidoOpt.isPresent()) {
			return ResponseEntity.ok(pedidoOpt.get());
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/v0/pedidos/{pedido.id}")
	public ResponseEntity<Pedido> alterar(@RequestBody Pedido pedido) {
		Pedido pedidoAlterado = pedidoService.alterar(pedido);
		return new ResponseEntity<>(pedidoAlterado, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/v0/pedidos/{pedidoId}")
	public void remover(@PathVariable("pedidoId") Long pedidoId) {
		pedidoService.excluir(pedidoId);
	}

}
