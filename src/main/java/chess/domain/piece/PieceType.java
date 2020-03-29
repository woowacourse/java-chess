package chess.domain.piece;

import static chess.domain.piece.Piece.*;
import static chess.domain.position.Direction.*;

import java.util.Arrays;

public enum PieceType {
	KING(new FixedMovingStrategy(Arrays.asList(
		UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
		KING_SCORE),
	QUEEN(new StretchMovingStrategy(Arrays.asList(
		UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
		QUEEN_SCORE),
	ROOK(new StretchMovingStrategy(Arrays.asList(
		UP, DOWN, LEFT, RIGHT)),
		ROOK_SCORE),
	BISHOP(new StretchMovingStrategy(Arrays.asList(
		LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN)),
		BISHOP_SCORE),
	KNIGHT(new FixedMovingStrategy(Arrays.asList(
		LEFT_LEFT_DOWN, LEFT_LEFT_UP,
		RIGHT_RIGHT_DOWN, RIGHT_RIGHT_UP,
		LEFT_DOWN_DOWN, LEFT_UP_UP,
		RIGHT_DOWN_DOWN, RIGHT_UP_UP)),
		KNIGHT_SCORE),
	BLACK_PAWN(new PawnMovingStrategy(Color.BLACK), PAWN_SCORE),
	WHITE_PAWN(new PawnMovingStrategy(Color.WHITE), PAWN_SCORE);

	private final MovingStrategy movingStrategy;
	private final double score;

	PieceType(MovingStrategy movingStrategy, double score) {
		this.movingStrategy = movingStrategy;
		this.score = score;
	}

	public MovingStrategy getMovingStrategy() {
		return movingStrategy;
	}

	public double getScore() {
		return score;
	}
}
