package br.edu.senior.devnapratica.pedidospdv.dao.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

public class ClienteServiceDAOTest {

	private ClienteDAO clienteDAO;
	private ClienteServiceDAO clienteService;
	
	@Before
	public void inicializar() {
		this.clienteDAO = new ClienteDAO();
		clienteDAO.salvar(new Cliente());
		clienteDAO.salvar(new Cliente());
		this.clienteService = new ClienteServiceDAO(this.clienteDAO);
	}
	
	@Test
	public void deveRetornarUmClienteParaId() {
		assertTrue(clienteService.buscar(1L).isPresent());
	}


}
