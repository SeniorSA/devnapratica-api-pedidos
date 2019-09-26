package br.edu.senior.devnapratica.pedidospdv.services;

import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTestMock {

	@Mock
	private ClienteDAO clienteDAO;
	private ClienteService clienteService;
	
	@Before
	public void inicializar() {
		this.clienteService = new ClienteService(this.clienteDAO);
	}
	
	@Test
	public void deveRetornarUmClienteParaId() {
		// Inicialização do cenário de teste.
		Cliente clienteMock = Mockito.mock(Cliente.class);
		
		Mockito
			.when(clienteDAO.buscar(1L))
			.thenReturn(Optional.ofNullable(clienteMock));
		
		// Execução do código alvo do teste.
		Optional<Cliente> clienteOpt = clienteService.buscar(1L);
		
		// Verificação dos resultados.
		Assert.assertTrue(clienteOpt.isPresent());
		Assert.assertEquals(clienteMock, clienteOpt.get());
		Mockito.verify(clienteDAO, atLeastOnce()).buscar(1L);
	}

}
