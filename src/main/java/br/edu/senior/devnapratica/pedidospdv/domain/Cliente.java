package br.edu.senior.devnapratica.pedidospdv.domain;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -2351535257357244460L;

	@Id
	@SequenceGenerator(name = "cliente_id_seq", sequenceName = "cliente_id_seq",
		initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_seq")
	private Long id;

	@NotEmpty(message = "O nome do cliente precisa ser preenchido!")
	@Column(length = 255)
	private String nome;

	@NotEmpty(message = "O email do cliente precisa ser preenchido!")
	@Column(length = 255)
	private String email;

	@NotNull(message = "A data de nascimento do cliente precisa ser preenchido!")
	@Column(name = "data_nascimento", length = 255)
	private LocalDate dataNascimento;

	public Cliente() {
	}
	
	public Cliente(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
