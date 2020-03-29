package chess.result;

import java.util.Map;
import java.util.Objects;

import chess.piece.Piece;

public class Score {
	private final double amount;

	public Score(Map<Piece, Boolean> pieces) {
		Objects.requireNonNull(pieces, "pieces의 정보가 없습니다.");
		this.amount = calculateScore(pieces);
	}

	private double calculateScore(Map<Piece, Boolean> pieces) {
		return pieces.keySet().stream()
			.mapToDouble(piece -> piece.getScore(pieces.get(piece)))
			.sum();
	}

	public double getAmount() {
		return amount;
	}

	public int compare(Score other) {
		return Double.compare(this.amount, other.amount);
	}
}
