package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDAO;
import br.edu.senior.devnapratica.pedidospdv.dao.PedidoDAO;
import br.edu.senior.devnapratica.pedidospdv.dao.ProdutoDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.domain.StatusPedido;

@Service
public class PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private ProdutoDAO produtoDAO;

	public Optional<Pedido> buscar(Long pedidoId) {
		return pedidoDAO.buscar(pedidoId);
	}

	public Pedido salvar(Pedido pedido) {
		this.validarPedido(pedido);
		
		pedido.setStatus(StatusPedido.PENDENTE);
		pedidoDAO.salvar(pedido);

		return pedido;
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

}
