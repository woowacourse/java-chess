package chess.domain.piece;

import java.util.Map;

import chess.domain.board.File;
import chess.domain.board.Position;

/**
 *    각 말의 점수를 의미하는 enum입니다.
 *
 *    @author AnHyungJu
 */
public enum Score {
	DEFAULT(0.0D),
	QUEEN(9.0D),
	ROOK(5.0D),
	BISHOP(3.0D),
	KNIGHT(2.5D),
	PAWN(1.0D),
	HALF_PAWN(0.5);

	private static final double INITIAL_SCORE = 0.0D;
	private static final int DEFAULT_PAWN_COUNT = 1;
	private double score;

	Score(double score) {
		this.score = score;
	}

	public static double calculateTotalScore(Map<Position, Piece> pieces) {
		return calculatePawnScore(pieces) + pieces.keySet().stream()
			.filter(position -> !pieces.get(position).isPawn())
			.mapToDouble(position -> pieces.get(position).score())
			.sum();
	}

	private static double calculatePawnScore(Map<Position, Piece> pieces) {
		double pawnsScore = INITIAL_SCORE;

		for (File file : File.values()) {
			int pawnCount = (int)pieces.keySet().stream()
				.filter(position -> (position.getColumn() == file.getColumn()) && (pieces.get(position).isPawn()))
				.count();
			if (pawnCount == DEFAULT_PAWN_COUNT) {
				pawnsScore += PAWN.getScore();
			} else {
				pawnsScore += (HALF_PAWN.getScore() * pawnCount);
			}
		}
		return pawnsScore;
	}

	public double getScore() {
		return score;
	}
}
