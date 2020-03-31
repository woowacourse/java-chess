package chess.domain.piece;

import static chess.domain.position.Direction.*;

import java.util.Arrays;

public enum PieceType {
	KING("k", new FixedMovingStrategy(Arrays.asList(
		UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
		0),
	QUEEN("q", new StretchMovingStrategy(Arrays.asList(
		UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN)),
		9),
	ROOK("r", new StretchMovingStrategy(Arrays.asList(
		UP, DOWN, LEFT, RIGHT)),
		5),
	BISHOP("b", new StretchMovingStrategy(Arrays.asList(
		LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN)),
		3),
	KNIGHT("n", new FixedMovingStrategy(Arrays.asList(
		LEFT_LEFT_DOWN, LEFT_LEFT_UP,
		RIGHT_RIGHT_DOWN, RIGHT_RIGHT_UP,
		LEFT_DOWN_DOWN, LEFT_UP_UP,
		RIGHT_DOWN_DOWN, RIGHT_UP_UP)),
		2.5),
	BLACK_PAWN("P", new PawnMovingStrategy(Color.BLACK), 1),
	WHITE_PAWN("p", new PawnMovingStrategy(Color.WHITE), 1);

	private final String name;
	private final MovingStrategy movingStrategy;
	private final double score;

	PieceType(String name, MovingStrategy movingStrategy, double score) {
		this.name = name;
		this.movingStrategy = movingStrategy;
		this.score = score;
	}

	public MovingStrategy getMovingStrategy() {
		return movingStrategy;
	}

	public double getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
}
