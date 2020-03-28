package chess.domain.game;

import java.util.List;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Score {
	private static final double PAWN_HALF_SCORE = 0.5;
	private static final double PAWN_COUNT = 2;

	private final double score;

	Score(double score) {
		this.score = score;
	}

	public static Score calculate(List<Piece> pieces) {
		return new Score(sum(pieces) - pawnScore(pieces));
	}

	private static double sum(List<Piece> pieces) {
		return pieces.stream()
				.mapToDouble(Piece::score)
				.sum();
	}

	private static double pawnScore(List<Piece> pieces) {
		int count = 0;
		for (int x = Position.BEGIN_X; x < Position.END_X; x++) {
			count += countOfPawnIfOverOne(pieces, x);
		}
		return PAWN_HALF_SCORE * count;
	}

	private static int countOfPawnIfOverOne(List<Piece> pieces, int x) {
		long count = pieces.stream()
				.filter(piece -> piece.isPawn() && piece.getPosition().equalsX(x))
				.count();
		if (count >= PAWN_COUNT) {
			return (int)count;
		}
		return 0;
	}

	public boolean isOverThan(Score that) {
		return this.score > that.score;
	}

	public double getValue() {
		return score;
	}
}
