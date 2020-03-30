package chess.result;

import java.util.List;
import java.util.Objects;

import chess.piece.Piece;

public class Score {
	private static final double HALF_VALUE = 0.5;

	private final double amount;

	public Score(List<Piece> pieces, int halfScorePawnCount) {
		Objects.requireNonNull(pieces, "pieces의 정보가 없습니다.");
		this.amount = calculateScore(pieces, halfScorePawnCount);
	}

	private double calculateScore(List<Piece> pieces, int halfScorePawnCount) {
		return pieces.stream()
			.mapToDouble(Piece::getScore)
			.reduce(0d, Double::sum) - (halfScorePawnCount * HALF_VALUE);
	}

	public double getAmount() {
		return amount;
	}

	public int compare(Score other) {
		return Double.compare(this.amount, other.amount);
	}
}
