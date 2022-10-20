package com.ennodo.resistence.infra.repository;

import com.ennodo.resistence.infra.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {
	@Modifying
	@Query("update Partida p set p.atual = false where p.atual = true and p.id <> :id")
	void atualizarPartidasAnteriores(Integer id);
}