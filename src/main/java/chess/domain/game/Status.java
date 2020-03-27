package chess.domain.game;

import chess.domain.piece.Color;

public class Status {
	private final Score white;
	private final Score black;

	public Status(Score white, Score black) {
		this.white = white;
		this.black = black;
	}

	public Color winner() {
		if (white.isOverThan(black)) {
			return Color.WHITE;
		}
		if (black.isOverThan(white)) {
			return Color.BLACK;
		}
		return Color.NONE;
	}

	public double getWhiteScore() {
		return white.getValue();
	}

	public double getBlackScore() {
		return black.getValue();
	}
}
