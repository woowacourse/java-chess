package chess.domain;

import java.util.Map;

import chess.domain.piece.Color;

public class GameResult {
	private final Map<Color, Double> result;

	public GameResult(Map<Color, Double> result) {
		this.result = result;
	}

	public double getScoreBy(Color color) {
		return result.get(color);
	}
}
