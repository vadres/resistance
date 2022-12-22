package com.ennodo.resistence.infra.service;

import com.ennodo.resistence.infra.entity.JogadorJpa;
import com.ennodo.resistence.infra.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogadorService {
	private final JogadorRepository jogadorRepository;

	public void criarJogador(String nome) {
		JogadorJpa jogadorJpa = new JogadorJpa(nome);
		jogadorRepository.save(jogadorJpa);
	}
}
