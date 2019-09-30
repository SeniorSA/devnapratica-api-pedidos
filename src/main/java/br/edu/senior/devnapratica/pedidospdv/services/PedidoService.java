package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.domain.ItemPedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Pedido;
import br.edu.senior.devnapratica.pedidospdv.domain.Produto;
import br.edu.senior.devnapratica.pedidospdv.domain.StatusPedido;
import br.edu.senior.devnapratica.pedidospdv.exception.EntidadeNaoEncontradaException;
import br.edu.senior.devnapratica.pedidospdv.repository.ClienteRepository;
import br.edu.senior.devnapratica.pedidospdv.repository.PedidoRepository;
import br.edu.senior.devnapratica.pedidospdv.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public PedidoService() {
	}
	
	PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,
			ProdutoRepository produtoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
		this.produtoRepository = produtoRepository;
	}

	public List<Pedido> buscarTodos() {
		return pedidoRepository.findAllEager();
	}

	public Optional<Pedido> buscar(Long pedidoId) {
		return pedidoRepository.findByIdEager(pedidoId);
	}

	public Pedido salvar(Pedido pedido) {
		this.validarPedido(pedido);

		pedido.setStatus(StatusPedido.PENDENTE);

		Double valorTotalPedido = 0.0;
		for (ItemPedido item : pedido.getItens()) {
			item.setPedido(pedido);
			valorTotalPedido += item.getQuantidade() * item.getProduto().getValor();
		}
		pedido.setValorTotal(valorTotalPedido);

		pedidoRepository.save(pedido);

		return pedido;
	}

	public Pedido alterar(Pedido pedido) {
		if (pedido.getStatus() != StatusPedido.PENDENTE) {
			throw new IllegalArgumentException("O pedido já foi finalizado ou cancelado e não pode ser alterado.");
		}

		Double valorTotalPedido = 0.0;
		for (ItemPedido item : pedido.getItens()) {
			item.setPedido(pedido);
			valorTotalPedido += item.getQuantidade() * item.getProduto().getValor();
		}
		pedido.setValorTotal(valorTotalPedido);

		return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
		if (pedido.getCliente() == null) {
			throw new IllegalArgumentException("O cliente não pode ser nulo!");
		}

		Optional<Cliente> clienteOpt = clienteRepository.findById(pedido.getCliente().getId());
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

			Optional<Produto> produtoOpt = produtoRepository.findById(itemPedido.getProduto().getId());
			if (!produtoOpt.isPresent()) {
				throw new IllegalArgumentException("O produto " + itemPedido.getProduto().getId() + " não existe!");
			}
			itemPedido.setProduto(produtoOpt.get());
		}
	}

	public void excluir(Long pedidoId) {
		pedidoRepository.deleteById(pedidoId);
	}

	public void finalizar(Long pedidoId) {
		this.alterarStatus(pedidoId, StatusPedido.FINALIZADO);
	}

	public void cancelar(Long pedidoId) {
		this.alterarStatus(pedidoId, StatusPedido.CANCELADO);
	}

	private void alterarStatus(Long pedidoId, StatusPedido novoStatus) {
		Pedido pedido = buscarPedido(pedidoId);
		if (!pedido.isPermiteEdicao()) {
			throw new IllegalArgumentException("O pedido está com status " + pedido.getStatus() + " e não pode ser alterado.");
		}
		
		pedido.setStatus(novoStatus);
		pedidoRepository.save(pedido);
	}

	private Pedido buscarPedido(Long pedidoId) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);

		if (!pedidoOpt.isPresent()) {
			throw new EntidadeNaoEncontradaException(Pedido.class, pedidoId);
		}

		return pedidoOpt.get();
	}

}
