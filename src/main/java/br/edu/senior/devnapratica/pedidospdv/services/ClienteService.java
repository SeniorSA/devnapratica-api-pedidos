package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteService() {
	}

	ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscar(Long clienteId) {
		return clienteRepository.findById(clienteId);
	}

	public Cliente salvar(Cliente cliente) {
		boolean temClienteComMesmoEmail = clienteRepository.countByEmail(cliente.getEmail()) > 0;

		if (temClienteComMesmoEmail) {
			throw new IllegalArgumentException("Já existe um cliente com este e-mail!");
		}

		return clienteRepository.save(cliente);
	}

	public Cliente alterar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
