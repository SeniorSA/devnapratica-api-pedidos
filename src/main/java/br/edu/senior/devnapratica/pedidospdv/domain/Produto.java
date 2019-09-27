package br.edu.senior.devnapratica.pedidospdv.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq",
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
	private Long id;

	@NotEmpty(message = "A descrição do produto precisa ser preenchida!")
	@Column(length = 255)
	private String descricao;

	@NotNull(message = "O valor do produto precisa ser preenchido!")
	@Range(min = 1, message = "O valor do produto precisa ser maior que zero!")
	private Double valor;

	public Produto() {
	}
	
	public Produto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
