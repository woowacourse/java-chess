package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {
	private Color color;

	public Turn(Color color) {
		validate(color);
		this.color = color;
	}

	private void validate(Color color) {
		if (color.isNone()) {
			throw new IllegalArgumentException("턴은 BLACK이나 WHITE로만 시작할 수 있습니다.");
		}
	}

	public void change() {
		if (color.isWhite()) {
			color = Color.BLACK;
			return;
		}
		color = Color.WHITE;
	}

	public Color getColor() {
		return color;
	}
}
