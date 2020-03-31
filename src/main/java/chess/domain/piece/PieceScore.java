package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum PieceScore {
	KING("K", 0),
	QUEEN("Q", 9),
	ROOK("R", 5),
	BISHOP("B", 3),
	KNIGHT("N", 2.5),
	PAWN("P", 1);

	private static final int PAWN_PENALTY_BOUND = 2;
	private static final double PAWN_PENALTY = 0.5;

	private final String name;
	private final double score;

	PieceScore(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public static double calculateScoreOf(List<Piece> column) {
		double score = column.stream()
			.mapToDouble(PieceScore::getScoreOf)
			.sum();
		return score - calculatePenalty(column);
	}

	private static double getScoreOf(Piece piece) {
		return Arrays.stream(values())
			.filter(value -> value.name.equals(piece.getName()))
			.findFirst()
			.orElseThrow(NullPointerException::new)
			.score;
	}

	private static double calculatePenalty(List<Piece> column) {
		if (countPawnIn(column) >= PAWN_PENALTY_BOUND) {
			return countPawnIn(column) * PAWN_PENALTY;
		}
		return 0;
	}

	private static long countPawnIn(List<Piece> column) {
		return column.stream()
			.filter(Piece::isPenaltyApplier)
			.count();
	}
}
