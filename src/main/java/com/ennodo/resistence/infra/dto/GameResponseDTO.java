package com.ennodo.resistence.infra.dto;

import com.ennodo.resistence.domain.CharacterEnum;
import com.ennodo.resistence.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GameResponseDTO {
	List<Player> players;
	CharacterEnum minhaPosicao;
	String observar;
}
