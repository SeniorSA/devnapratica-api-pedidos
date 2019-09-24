package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDAO;
import br.edu.senior.devnapratica.pedidospdv.dao.PedidoDAO;
import br.edu.senior.devnapratica.pedidospdv.dao.ProdutoDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.domain.StatusPedido;
import br.edu.senior.devnapratica.pedidospdv.util.ValidacaoUtils;

@Service
public class PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private ProdutoDAO produtoDAO;

	public List<Pedido> buscarTodos() {
		return pedidoDAO.buscarTodos();
	}

	public Optional<Pedido> buscar(Long pedidoId) {
		return pedidoDAO.buscar(pedidoId);
	}

	public Pedido salvar(Pedido pedido) {
		this.validarPedido(pedido);

		pedido.setStatus(StatusPedido.PENDENTE);
		pedidoDAO.salvar(pedido);

		return pedido;
	}

	public Pedido alterar(Pedido pedido) {
		if (pedido.getStatus() != StatusPedido.PENDENTE){
			throw new IllegalArgumentException("O pedido já foi finalizado ou cancelado e não pode ser alterado.");
		}

		this.validarPedido(pedido);
		return pedidoDAO.alterar(pedido);
	}

	private void validarPedido(Pedido pedido) {
		if (pedido.getCliente() == null) {
			throw new IllegalArgumentException("O cliente não pode ser nulo!");
		}

		Optional<Cliente> clienteOpt = clienteDAO.buscar(pedido.getCliente().getId());
		if (!clienteOpt.isPresent()) {
			throw new IllegalArgumentException("O cliente " + pedido.getCliente().getId() + " não existe!");
		}
		pedido.setCliente(clienteOpt.get());

		if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
			throw new IllegalArgumentException("O pedido precisa ter pelo menos um produto!");
		}

		for (ItemPedido itemPedido : pedido.getItens()) {
			if (itemPedido.getProduto() == null) {
				throw new IllegalArgumentException("O produto não pode ser nulo!");
			}

			Optional<Produto> produtoOpt = produtoDAO.buscar(itemPedido.getProduto().getId());
			if (!produtoOpt.isPresent()) {
				throw new IllegalArgumentException("O cliente " + pedido.getCliente().getId() + " não existe!");
			}
			itemPedido.setProduto(produtoOpt.get());
		}
	}

	public void excluir(Long pedidoId) {
		pedidoDAO.excluir(pedidoId);
	}

}
