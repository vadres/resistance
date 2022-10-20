package com.ennodo.resistence.infra.controller;

import com.ennodo.resistence.infra.dto.GameResponseDTO;
import com.ennodo.resistence.infra.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogador")
@RequiredArgsConstructor
public class JogadorController {

	private final GameService gameService;

	@GetMapping("/all")
	public ResponseEntity<GameResponseDTO> all() {
		return ResponseEntity.ok(gameService.buscarJogadores());
	}
}
