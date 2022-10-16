package com.ennodo.resistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum CharacterEnum {
	GUARDA_COSTAS("Comandantes"),
	COMANDANTE("Espiões"),
	ASSASSINO("Espiões"),
	FALSO_COMANDANTE("Espiões"),
	RESISTENCIA(""),
	ESPIAO("Espiões"),
	AGENTE_INVISIVEL("Espiões");

	@Getter
	private final String observer;

	public static boolean acharEspiao(Player player) {
		return ESPIAO.equals(player.characterEnum) ||
				ASSASSINO.equals(player.characterEnum) ||
				FALSO_COMANDANTE.equals(player.characterEnum);
	}

	public static CharacterEnum byOrdinal(Integer ordinal) {
		return Arrays.stream(CharacterEnum.values()).filter(c -> Integer.valueOf(c.ordinal()).equals(ordinal))
				.findFirst().orElse(RESISTENCIA);
	}

}
