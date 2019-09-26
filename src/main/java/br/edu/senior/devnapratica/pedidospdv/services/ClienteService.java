package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	public ClienteService() {
	}

	ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public List<Cliente> buscarTodos() {
		return clienteDAO.buscarTodos();
	}

	public Optional<Cliente> buscar(Long clienteId) {
		return clienteDAO.buscar(clienteId);
	}

	public Cliente salvar(Cliente cliente) {
		boolean temClienteComMesmoEmail = clienteDAO.buscarTodos().stream()
				.anyMatch(outroCliente -> outroCliente != null && outroCliente.getEmail().equals(cliente.getEmail()));

		if (temClienteComMesmoEmail) {
			throw new IllegalArgumentException("Já existe um cliente com este e-mail!");
		}

		return clienteDAO.salvar(cliente);
	}

	public Cliente alterar(Cliente cliente) {
		return clienteDAO.alterar(cliente);
	}

	public void excluir(Long clienteId) {
		clienteDAO.excluir(clienteId);
	}

}
