package br.edu.senior.devnapratica.pedidospdv.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.edu.senior.devnapratica.pedidospdv.DomainTestHelper;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.domain.StatusPedido;
import br.edu.senior.devnapratica.pedidospdv.repository.ClienteRepository;
import br.edu.senior.devnapratica.pedidospdv.repository.PedidoRepository;
import br.edu.senior.devnapratica.pedidospdv.repository.ProdutoRepository;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceMockTest {

	private Cliente cliente1;
	private Cliente cliente2;
	private Produto produto1;
	private Produto produto2;
	private Produto produto3;

	@Mock // A cada execução do teste, uma nova instância do mock é criada.
	private PedidoRepository pedidoRepository;

	@Mock // A cada execução do teste, uma nova instância do mock é criada.
	private ClienteRepository clienteRepository;

	@Mock // A cada execução do teste, uma nova instância do mock é criada.
	private ProdutoRepository produtoRepository;

	private PedidoService pedidoService;

	@Before
	public void init() {
		this.criarClientes().forEach(
				cliente -> Mockito.when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente)));
		this.criarProdutos().forEach(
				produto -> Mockito.when(produtoRepository.findById(produto.getId())).thenReturn(Optional.of(produto)));

		this.pedidoService = new PedidoService(pedidoRepository, clienteRepository, produtoRepository);
	}

	private List<Cliente> criarClientes() {
		cliente1 = DomainTestHelper.criarCliente(1L, "Luiz Nazari", "luiz.nazari@senior.com.br");
		cliente2 = DomainTestHelper.criarCliente(2L, "Matheus Raymundo", "matheus.raymundo@senior.com.br");
		return Arrays.asList(cliente1, cliente2);
	}

	private List<Produto> criarProdutos() {
		produto1 = DomainTestHelper.criarProduto(1L, "The Witcher 3: Wild Hunt – Complete Edition", 191.90);
		produto2 = DomainTestHelper.criarProduto(2L, "Borderlands 3 - Edição Superdeluxe", 499.50);
		produto3 = DomainTestHelper.criarProduto(3L, "Darksiders: Furys Collection - War and Death", 143.50);
		return Arrays.asList(produto1, produto2, produto3);
	}

	@Test
	public void deveRealizarPedidoComSucesso() {
		// Instanciação do pedido para salvar:
		ItemPedido item1 = new ItemPedido();
		item1.setProduto(produto1);
		item1.setQuantidade(2.0);

		ItemPedido item2 = new ItemPedido();
		item2.setProduto(produto2);
		item2.setQuantidade(1.0);

		Pedido pedido = new Pedido();
		pedido.setItens(Arrays.asList(item1, item2));
		pedido.setCliente(cliente1);

		// Realiza o pedido e salva no banco.
		Pedido pedidoSalvo = pedidoService.salvar(pedido);
		assertNotNull(pedidoSalvo);
		
		// A id sempre será null pois o pedido não foi verdadeiramente salvo.
		// assertNotNull(pedidoSalvo.getId());
		
		// Realiza as verificações sobre os atributos do pedido salvo.
		assertEquals(StatusPedido.PENDENTE, pedidoSalvo.getStatus());
		assertEquals(883.3, pedidoSalvo.getValorTotal(), 0.001);
		
		// Verifica se chamou o método para salvar o pedido.
		Mockito.verify(pedidoRepository).save(pedido);
	}

	@Test
	public void naoDeveRealizarPedidoComItensComProdutoInexistente() {
		// Instanciação do pedido para salvar:
		ItemPedido item1 = new ItemPedido();
		item1.setProduto(produto1);
		item1.setQuantidade(2.0);

		ItemPedido item2 = new ItemPedido();
		item2.setProduto(new Produto(999L));
		item2.setQuantidade(1.0);

		Pedido pedido = new Pedido();
		pedido.setItens(Arrays.asList(item1, item2));
		pedido.setCliente(cliente1);

		// Realiza o pedido e salva no banco.
		try {
			pedidoService.salvar(pedido);
			fail("Não deve chegar aqui, pois deve lançãr um erro de validacao");
		} catch (IllegalArgumentException e) {
			assertEquals("O produto 999 não existe!", e.getMessage());
		}
	}

	@Test
	public void naoDeveRealizarPedidoSemProdutos() {
		// Instanciação do pedido para salvar:
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente1);

		// Realiza o pedido e salva no banco.
		try {
			pedidoService.salvar(pedido);
			fail("Não deve chegar aqui, pois deve lançãr um erro de validacao");
		} catch (IllegalArgumentException e) {
			assertEquals("O pedido precisa ter pelo menos um produto!", e.getMessage());
		}
	}

	@Test
	public void naoDeveRealizarPedidoSemCliente() {
		// Instanciação do pedido para salvar:
		ItemPedido item1 = new ItemPedido();
		item1.setProduto(produto3);
		item1.setQuantidade(2.0);

		Pedido pedido = new Pedido();
		pedido.setItens(Arrays.asList(item1));

		// Realiza o pedido e salva no banco.
		try {
			pedidoService.salvar(pedido);
			fail("Não deve chegar aqui, pois deve lançãr um erro de validacao");
		} catch (IllegalArgumentException e) {
			assertEquals("O cliente não pode ser nulo!", e.getMessage());
		}
	}

	@Test
	public void deveAlterarStatusParaFinalizado() {
		// Cria e salva um pedido.
		Pedido pedido = criarESalvarUmPedido();

		// Chama o método para finalizar.
		pedidoService.finalizar(pedido.getId());

		// Busca o pedido no banco.
		Pedido pedidoAlterado = pedidoRepository.findById(pedido.getId()).get();

		// Verifica se o pedido foi finalizado e o status alterado corretamente.
		assertEquals(StatusPedido.FINALIZADO, pedidoAlterado.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAlterarStatusParaFinalizadoQuandoPedidoEstaCancelado() {
		// Cria e salva um pedido.
		Pedido pedido = criarESalvarUmPedido();

		// Altera manualmente o status para cancelado e salva no banco.
		pedido.setStatus(StatusPedido.CANCELADO);
		pedidoRepository.save(pedido);

		// Chama o método para finalizar.
		pedidoService.finalizar(pedido.getId());

		fail("Não deve chegar aqui, pois o método 'finalizar' irá lançar uma exceção.");
	}

	private Pedido criarESalvarUmPedido() {
		ItemPedido item1 = new ItemPedido();
		item1.setProduto(produto3);
		item1.setQuantidade(1.0);

		ItemPedido item2 = new ItemPedido();
		item2.setProduto(produto1);
		item2.setQuantidade(4.32);

		ItemPedido item3 = new ItemPedido();
		item3.setProduto(produto2);
		item3.setQuantidade(2.0);

		Pedido pedido = new Pedido();
		pedido.setItens(Arrays.asList(item1, item2, item3));
		pedido.setCliente(cliente2);

		Mockito.when(pedidoRepository.findById(pedido.getId())).thenReturn(Optional.of(pedido));
		return this.pedidoService.salvar(pedido);
	}

}
