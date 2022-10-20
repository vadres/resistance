package com.ennodo.resistence.infra.repository;

import com.ennodo.resistence.infra.entity.PartidaJogador;
import com.ennodo.resistence.infra.entity.PartidaJogadorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartidaJogadorRepository extends JpaRepository<PartidaJogador, PartidaJogadorId> {
	@Query("select pj from PartidaJogador pj " +
			"inner join fetch pj.personagem pe " +
			"inner join fetch pj.jogador j " +
			"inner join fetch pj.partida pa " +
			"inner join pa.grupo pag " +
			"where pa.atual = true and pag.atual = true ")
	List<PartidaJogador> buscarJogadoresJogoAtual();
}