package chess.domain.piece.kind;

import java.util.Optional;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public class Knight extends Piece {
	private static final double KNIGHT_SCORE = 2.5;
	private static final int POSSIBLE_DISTANCE_OF_KNIGHT = 5;
	private static final String KNIGHT_NAME = "n";

	public Knight(Color color, Point point) {
		super(KNIGHT_NAME, color, point);
	}

	@Override
	public Optional<Direction> direction(Piece target) {
		int distance = this.point.calculateDistance(target.point);

		if (distance != POSSIBLE_DISTANCE_OF_KNIGHT) {
			throw new IllegalArgumentException(IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
		}
		return Optional.empty();
	}

	@Override
	public Point moveOneStep(Point target, Direction direction) {
		return target;
	}

	@Override
	public double score() {
		return KNIGHT_SCORE;
	}

	@Override
	public boolean isEmptyPiece() {
		return false;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public boolean isPawn() {
		return false;
	}
}
