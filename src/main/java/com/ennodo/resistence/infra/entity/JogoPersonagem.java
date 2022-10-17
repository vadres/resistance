package com.ennodo.resistence.infra.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "jogo_personagem")
public class JogoPersonagem {
	@EmbeddedId
	private JogoPersonagemId id;

	@MapsId("idPersonagem")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_personagem", nullable = false)
	private Personagem idPersonagem;

	@MapsId("idJogo")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_jogo", nullable = false)
	private Jogo idJogo;

	@Column(name = "qtd_personagem", nullable = false)
	private Integer qtdPersonagem;

	public JogoPersonagemId getId() {
		return id;
	}

	public void setId(JogoPersonagemId id) {
		this.id = id;
	}

	public Personagem getIdPersonagem() {
		return idPersonagem;
	}

	public void setIdPersonagem(Personagem idPersonagem) {
		this.idPersonagem = idPersonagem;
	}

	public Jogo getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Jogo idJogo) {
		this.idJogo = idJogo;
	}

	public Integer getQtdPersonagem() {
		return qtdPersonagem;
	}

	public void setQtdPersonagem(Integer qtdPersonagem) {
		this.qtdPersonagem = qtdPersonagem;
	}

}