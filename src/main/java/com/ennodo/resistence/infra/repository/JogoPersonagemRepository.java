package com.ennodo.resistence.infra.repository;

import com.ennodo.resistence.infra.entity.JogoPersonagem;
import com.ennodo.resistence.infra.entity.JogoPersonagemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoPersonagemRepository extends JpaRepository<JogoPersonagem, JogoPersonagemId> {
	@Query("select jp from JogoPersonagem jp " +
			"join fetch jp.personagem p " +
			"join fetch jp.jogo j " +
			"where j.id = :idJogo")
	List<JogoPersonagem> buscarPersonagens(Integer idJogo);
}