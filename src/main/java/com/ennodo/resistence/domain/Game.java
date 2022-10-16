package com.ennodo.resistence.domain;

import com.ennodo.resistence.infra.dto.AllPlayersDTO;
import com.ennodo.resistence.infra.dto.GameResponseDTO;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.ennodo.resistence.domain.CharacterEnum.COMANDANTE;
import static com.ennodo.resistence.domain.CharacterEnum.FALSO_COMANDANTE;

@Getter
public class Game {
	List<Player> players;
    Map<Integer, Integer> charactersMap;

	public Game(AllPlayersDTO playersDTO) {
		Collections.shuffle(playersDTO.getPlayers(), new Random());
		players = new ArrayList<>();
		int playersQtd = playersDTO.getPlayers().size();
		int characterGenerated = 0;

		charactersMap = new HashMap<>();
		for (int i = 0; i < 5; i++) charactersMap.put(i, 0);

		do {
			Integer character = getRandomNumber(0, 5);
			if (character.equals(4)) {
				if (charactersMap.get(character) < 1) {
					characterGenerated = getCharacterGenerated(playersDTO, characterGenerated, character);
				}
			} else {
				if (charactersMap.get(character) < 1) {
					characterGenerated = getCharacterGenerated(playersDTO, characterGenerated, character);
				}
			}
		} while (characterGenerated < playersQtd);

	}

	private int getCharacterGenerated(AllPlayersDTO playersDTO, int characterGenerated, Integer character) {
		charactersMap.put(character, charactersMap.get(character) + 1);
		players.add(new Player(playersDTO.getPlayers().get(characterGenerated), character));
		characterGenerated++;
		return characterGenerated;
	}

	public GameResponseDTO search(String name) {
		List<Player> playersResponse;
		Player player = players.stream().filter(p -> p.getName().equals(name))
				.findFirst().orElse(new Player("", 99));

		switch (player.getCharacterEnum()) {
			case COMANDANTE, FALSO_COMANDANTE, ASSASSINO, ESPIAO, AGENTE_INVISIVEL -> playersResponse = players.stream()
					.filter(CharacterEnum::acharEspiao)
					.toList();
			case GUARDA_COSTAS ->  playersResponse = players.stream()
					.filter(p -> COMANDANTE.equals(p.getCharacterEnum()) || FALSO_COMANDANTE.equals(p.getCharacterEnum()))
					.toList();
			case RESISTENCIA, default ->  playersResponse = new ArrayList<>();
		}

		return new GameResponseDTO(playersResponse, player.characterEnum, player.characterEnum.getObserver());
	}

	private Integer getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
