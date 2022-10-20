package com.ennodo.resistence.infra.service;

import com.ennodo.resistence.infra.dto.GameResponseDTO;
import com.ennodo.resistence.infra.dto.JogadorDTO;
import com.ennodo.resistence.infra.entity.GrupoPartida;
import com.ennodo.resistence.infra.entity.Jogador;
import com.ennodo.resistence.infra.entity.Jogo;
import com.ennodo.resistence.infra.entity.JogoPersonagem;
import com.ennodo.resistence.infra.entity.Partida;
import com.ennodo.resistence.infra.entity.PartidaJogador;
import com.ennodo.resistence.infra.entity.PartidaJogadorId;
import com.ennodo.resistence.infra.repository.GrupoPartidaRepository;
import com.ennodo.resistence.infra.repository.JogadorRepository;
import com.ennodo.resistence.infra.repository.JogoPersonagemRepository;
import com.ennodo.resistence.infra.repository.JogoRepository;
import com.ennodo.resistence.infra.repository.PartidaJogadorRepository;
import com.ennodo.resistence.infra.repository.PartidaRepository;
import com.ennodo.resistence.infra.repository.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {
	private final GrupoPartidaRepository grupoPartidaRepository;
	private final JogadorRepository jogadorRepository;
	private final JogoRepository jogoRepository;
	private final PartidaJogadorRepository partidaJogadorRepository;
	private final PartidaRepository partidaRepository;
	private final JogoPersonagemRepository jogoPersonagemRepository;
	private final PersonagemRepository personagemRepository;

	public GameResponseDTO buscarJogadores() {
		List<PartidaJogador> partidaJogadores = partidaJogadorRepository.buscarJogadoresJogoAtual();

		if (partidaJogadores == null || partidaJogadores.isEmpty()) {
			List<Jogador> jogadores = jogadorRepository.findAll();
			return new GameResponseDTO(
					jogadores.stream().map(JogadorDTO::toDTO).toList(),
					GameResponseDTO.Fase.JOGO_NOVO.getValor()
			);
		}

		return new GameResponseDTO(
				partidaJogadores.stream().map(partidaJogador -> (
					new JogadorDTO(partidaJogador.getJogador().getId(),
							partidaJogador.getJogador().getNome(),
							partidaJogador.getPersonagem().getDescricao(),
							partidaJogador.getPersonagem().getInfo(),
							revelados(partidaJogador, partidaJogadores)
					)
				)).toList(),
				GameResponseDTO.Fase.JOGO_JA_INICIADO.getValor()
		);
	}

	@Transactional
	public List<JogadorDTO> iniciarJogo(List<JogadorDTO> jogadoresDTO) {
		List<Jogador> jogadores = jogadorRepository.buscarPorIds(
			jogadoresDTO.stream().map(JogadorDTO::getId).toList()
		);

		Collections.shuffle(jogadores, new Random());

		Jogo jogo = jogoRepository.findByQtdJogadores(jogadores.size());

		List<JogoPersonagem> jogoPersonagems = jogoPersonagemRepository
				.buscarPersonagens(jogo.getId());
		Collections.shuffle(jogoPersonagems, new Random());

		GrupoPartida grupoPartida = new GrupoPartida();
		grupoPartida.setAtual(true);
		grupoPartida = grupoPartidaRepository.save(grupoPartida);
		grupoPartidaRepository.atualizarGruposAnteriores(grupoPartida.getId());

		Partida partida = new Partida();
		partida.setGrupo(grupoPartida);
		partida.setJogo(jogo);
		partida.setAtual(true);
		partida = partidaRepository.save(partida);
		partidaRepository.atualizarPartidasAnteriores(partida.getId());

		final List<PartidaJogador> partidaJogadores = new ArrayList<>();
		int i = 0;
		for (Jogador jogador: jogadores) {
			JogoPersonagem jogoPersonagem = jogoPersonagems.get(i);
			if (jogoPersonagem.getQtdPersonagem() == 1) i++;
			else jogoPersonagem.setQtdPersonagem( jogoPersonagem.getQtdPersonagem() - 1);

			PartidaJogadorId partidaJogadorId = new PartidaJogadorId(jogador.getId(), partida.getId());
			PartidaJogador partidaJogador = new PartidaJogador();
			partidaJogador.setId(partidaJogadorId);
			partidaJogador.setPartida(partida);
			partidaJogador.setPersonagem(jogoPersonagem.getPersonagem());
			partidaJogador.setJogador(jogador);

			partidaJogadores.add(partidaJogadorRepository.save(partidaJogador));
		}

		return partidaJogadores.stream().map(partidaJogador -> (
			new JogadorDTO(partidaJogador.getJogador().getId(),
					partidaJogador.getJogador().getNome(),
					partidaJogador.getPersonagem().getDescricao(),
					partidaJogador.getPersonagem().getInfo(),
					revelados(partidaJogador, partidaJogadores)
			)
		)).toList();
	}

	private List<String> revelados(PartidaJogador partidaJogador,  List<PartidaJogador> partidaJogadores) {
		return partidaJogadores.stream()
				.filter(pj -> partidaJogador.getIds().contains(pj.getJogador().getId()))
				.map(pj -> pj.getJogador().getNome())
				.toList();
	}

}
