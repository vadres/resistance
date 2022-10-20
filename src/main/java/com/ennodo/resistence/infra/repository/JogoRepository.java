package com.ennodo.resistence.infra.repository;

import com.ennodo.resistence.infra.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
	Jogo findByQtdJogadores(Integer qtdJogadores);
}