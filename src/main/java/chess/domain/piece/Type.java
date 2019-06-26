package chess.domain.piece;

import chess.domain.Score;

public enum Type {
	PAWN(1.0),
	KNIGHT(2.5),
	BISHOP(3.0),
	ROOK(5.0),
	KING(0),
	QUEEN(9.0),
	EMPTY(0);

	private double score;

	Type(final double score) {
		this.score = score;
	}

	public Score getScore() {
		return new Score(score);
	}
}
