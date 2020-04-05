package chess.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.Game;
import chess.domain.piece.Team;
import chess.view.dto.ScoreDTO;

public class GameService {
	private final Game game;

	public GameService(Game game) {
		this.game = game;
	}

	public List<ScoreDTO> calculateScore() {
		Map<Team, Double> status = game.status();
		return status.entrySet().stream()
			.map(entry -> new ScoreDTO(entry.getKey().name().toLowerCase(), entry.getValue()))
			.collect(Collectors.toList());
	}
}
