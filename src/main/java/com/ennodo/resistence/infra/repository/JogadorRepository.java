package com.ennodo.resistence.infra.repository;

import com.ennodo.resistence.infra.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
	@Query("select j from Jogador j where id in :toList")
	List<Jogador> buscarPorIds(List<Integer> toList);
}
