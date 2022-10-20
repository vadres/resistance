package com.ennodo.resistence.infra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jogador")
public class Jogador {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}