package br.edu.senior.devnapratica.pedidospdv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@Id
	@SequenceGenerator(name = "item_pedido_id_seq", sequenceName = "item_pedido_id_seq",
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_pedido_id_seq")
	private Long id;

	@NotNull(message = "O item precisa estar relacionado a um pedido!")
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(
		name = "id_pedido",
		nullable = false,
		foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"))
	private Pedido pedido;

	@NotNull(message = "O item precisa estar relacionado a um produto!")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name = "id_produto",
			nullable = false,
			foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
	private Produto produto;

	@NotNull(message = "A quantidade do item precisa ser preenchida!")
	@Range(min = 1, message = "A quantidade do item precisa ser maior que zero!")
	@Column(nullable = false)
	private Double quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return this.produto.getValor() * this.quantidade;
	}

}
