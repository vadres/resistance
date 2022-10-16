package com.ennodo.resistence.infra.dto;

import com.ennodo.resistence.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerResponseDTO {
	String name;
	String function;

	public static PlayerResponseDTO toPlayerResponseDTO(Player player) {
		return new PlayerResponseDTO(player.getName(), player.getCharacterEnum().name());
	}
}
