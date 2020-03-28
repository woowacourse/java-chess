package chess.Score;

import java.util.Map;

import chess.piece.type.Piece;

public class Score {
	private final double amount;

	public Score(Map<Piece, Boolean> pieces) {
		this.amount = calculateScore(pieces);
	}

	public double calculateScore(Map<Piece, Boolean> pieces) {
		return pieces.keySet().stream()
			.mapToDouble(piece -> piece.getScore(pieces.get(piece)))
			.sum();
	}

	public double getAmount() {
		return amount;
	}
}
