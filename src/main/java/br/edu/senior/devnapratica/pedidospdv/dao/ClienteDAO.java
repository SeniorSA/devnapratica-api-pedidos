package br.edu.senior.devnapratica.pedidospdv.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

@Component
public class ClienteDAO {

	private static AtomicLong idSequence = new AtomicLong(1L);
	private HashMap<Long, Cliente> clientesRegistrados = new LinkedHashMap<>();

	public List<Cliente> buscarTodos() {
		return new LinkedList<Cliente>(clientesRegistrados.values());
	}
	
	public Optional<Cliente> buscar(Long clienteId) {
		if (clienteId == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(clientesRegistrados.get(clienteId));
	}
	
	public Cliente salvar(Cliente cliente) {
		cliente.setId(idSequence.getAndIncrement());
		clientesRegistrados.put(cliente.getId(), cliente);
		return cliente;
	}
	
	public Cliente alterar(Cliente cliente) {
		clientesRegistrados.put(cliente.getId(), cliente);
		return cliente;
	}
	
	public void excluir(Long clienteId) {
		clientesRegistrados.remove(clienteId);
	}
	
}
