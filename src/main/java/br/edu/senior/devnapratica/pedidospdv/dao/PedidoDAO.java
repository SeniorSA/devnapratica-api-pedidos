package br.edu.senior.devnapratica.pedidospdv.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;

@Component
public class PedidoDAO {

	private static AtomicLong idSequence = new AtomicLong(0L);
	private HashMap<Long, Pedido> pedidosRegistrados = new LinkedHashMap<>();

	public List<Pedido> buscarTodos() {
		return new LinkedList<Pedido>(pedidosRegistrados.values());
	}
	
	public Optional<Pedido> buscar(Long pedidoId) {
		return Optional.ofNullable(pedidosRegistrados.get(pedidoId));
	}
	
	public Pedido salvar(Pedido pedido) {
		pedido.setId(idSequence.getAndIncrement());
		pedidosRegistrados.put(pedido.getId(), pedido);
		return pedido;
	}
	
	public Pedido alterar(Pedido pedido) {
		pedidosRegistrados.put(pedido.getId(), pedido);
		return pedido;
	}
	
	public void excluir(Long pedidoId) {
		pedidosRegistrados.remove(pedidoId);
	}
	
}
