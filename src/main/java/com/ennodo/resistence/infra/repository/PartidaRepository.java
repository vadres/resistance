package com.ennodo.resistence.infra.repository;

import com.ennodo.resistence.infra.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {
}