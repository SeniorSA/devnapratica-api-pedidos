package br.edu.senior.devnapratica.pedidospdv.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.edu.senior.devnapratica.pedidospdv.DomainTestHelper;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.domain.StatusPedido;
import br.edu.senior.devnapratica.pedidospdv.repository.ClienteRepository;
import br.edu.senior.devnapratica.pedidospdv.repository.PedidoRepository;
import br.edu.senior.devnapratica.pedidospdv.repository.ProdutoRepository;
import br.edu.senior.devnapratica.pedidospdv.services.PedidoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoControllerIntegrationTest {

	private Cliente cliente1;
	private Cliente cliente2;
	private Produto produto1;
	private Produto produto2;
	private Produto produto3;

	@Autowired
	protected WebApplicationContext context;

	protected MockMvc mvc;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoService pedidoService;

	@Before
	public void init() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Before
	public void inserirDadosNaBase() {
		this.clienteRepository.saveAll(this.criarClientes());
		this.produtoRepository.saveAll(this.criarProdutos());
	}

	@After
	public void limparBase() {
		this.pedidoRepository.deleteAll();
		this.produtoRepository.deleteAll();
		this.clienteRepository.deleteAll();
	}

	private List<Cliente> criarClientes() {
		cliente1 = DomainTestHelper.criarCliente(null, "Luiz Nazari", "luiz.nazari@senior.com.br");
		cliente2 = DomainTestHelper.criarCliente(null, "Matheus Raymundo", "matheus.raymundo@senior.com.br");
		return Arrays.asList(cliente1, cliente2);
	}

	private List<Produto> criarProdutos() {
		produto1 = DomainTestHelper.criarProduto(null, "The Witcher 3: Wild Hunt – Complete Edition", 191.90);
		produto2 = DomainTestHelper.criarProduto(null, "Borderlands 3 - Edição Superdeluxe", 499.50);
		produto3 = DomainTestHelper.criarProduto(null, "Darksiders: Furys Collection - War and Death", 143.50);
		return Arrays.asList(produto1, produto2, produto3);
	}

	// =-=-=-=-=-=-=-=-=-=-=: Testes :=-=-=-=-=-=-=-=-=-=-=

	@Test
	public void deveRetornarErroAoSalvarPedidoVazio() throws Exception {
		// @formatter:off
		mvc
			.perform(MockMvcRequestBuilders.post("/v1/pedidos")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("{}"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		// @formatter:on
	}

	@Test
	public void deveSalvarPedidoComSucesso() throws Exception {
		// @formatter:off
		mvc
			.perform(
				post("/v1/pedidos")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("{" + 
							"	\"cliente\": {" + 
							"		\"id\": " + cliente1.getId() + 
							"	}," + 
							"	\"itens\": [" + 
							"		{" + 
							"			\"produto\": {" + 
							"				\"id\": " + produto2.getId() +
							"			}," + 
							"			\"quantidade\": 1" + 
							"		}," + 
							"		{" + 
							"			\"produto\": {" + 
							"				\"id\": " + produto3.getId() +
							"			}," + 
							"			\"quantidade\": 0.42" + 
							"		}" + 
							"	]" + 
							"}")
			)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").exists())
			.andExpect(jsonPath("$.id").isNumber())
			.andExpect(jsonPath("$.cliente.id").value(cliente1.getId()))
			.andExpect(jsonPath("$.status").value(StatusPedido.PENDENTE.name()));
		// @formatter:on
	}
	
	@Test
	public void deveListarTodosOsPedidosQuandoNaoTemPedidosSalvos() throws Exception {
		// @formatter:off
		mvc
			.perform(get("/v1/pedidos"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").exists())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isEmpty());
		// @formatter:on
	}
	
	@Test
	public void deveListarTodosOsPedidosSalvos() throws Exception {
		criarESalvarUmPedido();
		criarESalvarUmPedido();
		
		// @formatter:off
		mvc
			.perform(get("/v1/pedidos"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").exists())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isNotEmpty())
			.andExpect(jsonPath("$[0]").exists())
			.andExpect(jsonPath("$[1]").exists())
			.andExpect(jsonPath("$[2]").doesNotExist())
			.andExpect(jsonPath("$[0].itens[0].produto.id").isNumber());
		// @formatter:on
	}
	
	private Pedido criarESalvarUmPedido() {
		ItemPedido item1 = new ItemPedido();
		item1.setProduto(produto3);
		item1.setQuantidade(1.0);

		Pedido pedido = new Pedido();
		pedido.setItens(Arrays.asList(item1));
		pedido.setCliente(cliente1);

		return this.pedidoService.salvar(pedido);
	}

}
