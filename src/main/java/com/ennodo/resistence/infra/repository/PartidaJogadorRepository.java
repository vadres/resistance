package com.ennodo.resistence.infra.repository;

import com.ennodo.resistence.infra.entity.PartidaJogador;
import com.ennodo.resistence.infra.entity.PartidaJogadorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaJogadorRepository extends JpaRepository<PartidaJogador, PartidaJogadorId> {
}