package com.ennodo.resistence.infra.dto;

import com.ennodo.resistence.infra.entity.Jogador;
import com.ennodo.resistence.infra.entity.JogoPersonagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDTO {
	private Integer id;
	private String nome;
	private String personagem;
	private String info;
	private List<String> revelados;

	public static JogadorDTO toDTO(Jogador jogador) {
		JogadorDTO dto = new JogadorDTO();
		dto.setId(jogador.getId());
		dto.setNome(jogador.getNome());
		return dto;
	}
}
