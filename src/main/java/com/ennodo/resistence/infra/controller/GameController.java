package com.ennodo.resistence.infra.controller;

import com.ennodo.resistence.domain.Game;
import com.ennodo.resistence.domain.Player;
import com.ennodo.resistence.infra.dto.PlayerResponseDTO;
import com.ennodo.resistence.infra.dto.AllPlayersDTO;
import com.ennodo.resistence.infra.dto.GameResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/resistance")
public class GameController {
	Game game;

	@PostMapping("/init")
	public ResponseEntity<List<String>> initGame(@RequestBody AllPlayersDTO playersDTO) {
		game = new Game(playersDTO);
		return ResponseEntity.ok(game.getPlayers().stream().map(Player::getName).toList());
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<GameResponseDTO> search(@PathVariable String name) {
		return ResponseEntity.ok(game.search(name));
	}

	@GetMapping("/reveal")
	public ResponseEntity<List<PlayerResponseDTO>> reveal() {
		return ResponseEntity.ok(game.getPlayers().stream().map(PlayerResponseDTO::toPlayerResponseDTO).toList());
	}
}
