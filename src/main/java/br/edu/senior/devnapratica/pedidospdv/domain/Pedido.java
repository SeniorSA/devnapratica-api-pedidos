package br.edu.senior.devnapratica.pedidospdv.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@SequenceGenerator(name = "pedido_id_seq", sequenceName = "pedido_id_seq",
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_id_seq")
	private Long id;

	@NotNull(message = "O pedido precisa ter um cliente associado!")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "id_cliente",
			nullable = false,
			foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
	private Cliente cliente;

	@NotEmpty(message = "O pedido precisa ter pelo menos um item!")
	@OneToMany(
			mappedBy = "pedido",
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	private List<ItemPedido> itens;

	@Column(nullable = false)
	private StatusPedido status;

	@Column(name = "valor_total", nullable = false)
	private Double valorTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public boolean isPermiteEdicao() {
		return this.status == StatusPedido.PENDENTE;
	}
	
}
