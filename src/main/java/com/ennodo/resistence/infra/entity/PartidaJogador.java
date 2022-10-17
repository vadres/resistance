package com.ennodo.resistence.infra.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "partida_jogador")
public class PartidaJogador {
	@EmbeddedId
	private PartidaJogadorId id;

	@MapsId("idJogador")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_jogador", nullable = false)
	private Jogador idJogador;

	@MapsId("idPartida")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_partida", nullable = false)
	private Partida idPartida;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_personagem", nullable = false)
	private Personagem idPersonagem;

	public PartidaJogadorId getId() {
		return id;
	}

	public void setId(PartidaJogadorId id) {
		this.id = id;
	}

	public Jogador getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(Jogador idJogador) {
		this.idJogador = idJogador;
	}

	public Partida getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(Partida idPartida) {
		this.idPartida = idPartida;
	}

	public Personagem getIdPersonagem() {
		return idPersonagem;
	}

	public void setIdPersonagem(Personagem idPersonagem) {
		this.idPersonagem = idPersonagem;
	}

}