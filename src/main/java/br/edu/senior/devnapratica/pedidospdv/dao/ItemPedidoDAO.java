package br.edu.senior.devnapratica.pedidospdv.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido;

@Component
public class ItemPedidoDAO {

	private static AtomicLong idSequence = new AtomicLong(0L);
	private HashMap<Long, ItemPedido> itemPedidosRegistrados = new LinkedHashMap<>();

	public List<ItemPedido> buscarTodos() {
		return new LinkedList<br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido>(itemPedidosRegistrados.values());
	}

	public Optional<ItemPedido> buscar(Long itemPedidoId) {
		return Optional.ofNullable(itemPedidosRegistrados.get(itemPedidoId));
	}

	public ItemPedido salvar(ItemPedido itemPedido) {
		itemPedido.setId(idSequence.getAndIncrement());
		itemPedidosRegistrados.put(itemPedido.getId(), itemPedido);
		return itemPedido;
	}

	public ItemPedido alterar(ItemPedido itemPedido) {
		itemPedidosRegistrados.put(itemPedido.getId(), itemPedido);
		return itemPedido;
	}

	public void excluir(Long itemPedidoId) {
		itemPedidosRegistrados.remove(itemPedidoId);
	}

}
