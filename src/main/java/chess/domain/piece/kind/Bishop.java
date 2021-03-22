package chess.domain.piece.kind;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static chess.domain.piece.Direction.*;

public class Bishop extends Piece {
	private static final String BISHOP_NAME = "b";
	private static final int BISHOP_SCORE = 3;

	private static final List<Direction> bishopDirection = Arrays.asList(SOUTH_EAST, SOUTH_WEST, NORTH_EAST,
		NORTH_WEST);

	public Bishop(Color color, Point point) {
		super(BISHOP_NAME, color, point);
	}

	@Override
	public Optional<Direction> direction(Piece target) {
		Direction direction = Direction.findDirection(this.point, target.point);
		if (!bishopDirection.contains(direction)) {
			throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
		}
		return Optional.of(direction);
	}

	@Override
	public Point moveOneStep(Point target, Direction direction) {
		return this.point.createNextPoint(direction);
	}

	@Override
	public double score() {
		return BISHOP_SCORE;
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
