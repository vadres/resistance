package com.ennodo.resistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Player {
	public Player(String name, Integer character) {
		this.characterEnum = CharacterEnum.byOrdinal(character);
		this.name = name;
	}

	String name;
	@JsonIgnore
	CharacterEnum characterEnum;
}
