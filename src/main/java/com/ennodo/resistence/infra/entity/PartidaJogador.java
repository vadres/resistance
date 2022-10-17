package com.ennodo.resistence.infra.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Table(name = "partida_jogador")
public class PartidaJogador {
	@EmbeddedId
	private PartidaJogadorId id;

	@MapsId("idJogador")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_jogador", nullable = false)
	private Jogador jogador;

	@MapsId("idPartida")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_partida", nullable = false)
	private Partida partida;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_personagem", nullable = false)
	private Personagem personagem;

	public PartidaJogadorId getId() {
		return id;
	}

	public void setId(PartidaJogadorId id) {
		this.id = id;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem pPersonagem) {
		this.personagem = pPersonagem;
	}

	public List<Integer> getIds() {
		return Arrays.stream(personagem.getIdsInfo().split(","))
				.map(Integer::valueOf).toList();
	}
}