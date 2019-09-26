package br.edu.senior.devnapratica.pedidospdv.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

public class ClienteServiceTest {

	private ClienteDAO clienteDAO;
	private ClienteService clienteService;
	
	@Before
	public void inicializar() {
		this.clienteDAO = new ClienteDAO();
		clienteDAO.salvar(new Cliente());
		clienteDAO.salvar(new Cliente());
		this.clienteService = new ClienteService(this.clienteDAO);
	}
	
	@Test
	public void deveRetornarUmClienteParaId() {
		assertTrue(clienteService.buscar(1L).isPresent());
	}

}
