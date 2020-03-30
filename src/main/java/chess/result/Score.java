package chess.result;

import java.util.List;
import java.util.Objects;

import chess.piece.Piece;

public class Score {
	private final double amount;

	public Score(List<Piece> pieces, int halfScorePawnCount) {
		Objects.requireNonNull(pieces, "pieces의 정보가 없습니다.");
		this.amount = calculateScore(pieces, halfScorePawnCount);
	}

	// 리스트 + 갯수
	private double calculateScore(List<Piece> pieces, int halfScorePawnCount) { // 세로가 겹치는 폰의 갯수만 넣으면 된다?
		return pieces.stream()
			.mapToDouble(Piece::getScore)
			.reduce(0d, Double::sum) - (halfScorePawnCount * 0.5);
	}

	public double getAmount() {
		return amount;
	}

	public int compare(Score other) {
		return Double.compare(this.amount, other.amount);
	}
}
