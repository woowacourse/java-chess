package chess.domain.state;

import java.util.List;

import chess.domain.piece.Piece;

public class Score {
	private static final double PAWN_HALF_SCORE = 0.5;
	private static final long PAWN_COUNT = 2;

	private final double score;

	private Score(double score) {
		this.score = score;
	}

	public static Score of(int score) {
		return new Score(score);
	}

	public Boolean isBiggerThan(Score other) {
		return this.score - other.score > 0;
	}

	public static Score calculate(List<Piece> pieces) {
		return new Score(sum(pieces) - pawnScore(pieces));
	}

	private static double sum(List<Piece> pieces) {
		return pieces.stream()
			.mapToDouble(Piece::getScore)
			.sum();
	}

	private static double pawnScore(List<Piece> pieces) {
		int count = 0;
		for (int x = 0; x < 8; x++) {
			count += countOfPawnIfOverOne(pieces, x);
		}
		return PAWN_HALF_SCORE * count;
	}

	private static int countOfPawnIfOverOne(List<Piece> pieces, int x) {
		long count = pieces.stream()
			.filter(piece -> piece.isPawn() && piece.getPosition().equalsX(x))
			.count();
		if (count >= PAWN_COUNT) {
			return (int) count;
		}
		return 0;
	}

	public double getScore() {
		return score;
	}
}
