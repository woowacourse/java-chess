package chess.domain.move;

import java.util.List;
import java.util.Set;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public enum Direction {

	RIGHT(1, 0),
	LEFT(-1, 0),
	UP(0, 1),
	DOWN(0, -1),
	RIGHT_UP(1, 1),
	LEFT_UP(-1, 1),
	RIGHT_DOWN(1, -1),
	LEFT_DOWN(-1, -1),
	KNIGHT_UP_LEFT(-1, 2),
	KNIGHT_UP_RIGHT(1, 2),
	KNIGHT_RIGHT_UP(2, 1),
	KNIGHT_RIGHT_DOWN(2, -1),
	KNIGHT_DOWN_RIGHT(1, -2),
	KNIGHT_DOWN_LEFT(-1, -2),
	KNIGHT_LEFT_DOWN(-2, -1),
	KNIGHT_LEFT_UP(-2, 1);

	private final int dx;
	private final int dy;

	Direction(final int dx, final int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public static Direction calculateDirection(Position source, Position target, final Piece piece) {
		int dx = diffFile(source, target);
		int dy = diffRank(source, target);
		Set<Direction> directions = piece.direction();
		Direction unit = findDirection(dx, dy);

		return directions.stream()
			.filter((direction) -> direction == unit)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("체스말이 이동할 수 없는 위치입니다"));
	}

	private static Direction findDirection(final int dx, final int dy) {
		if (dx == 0) {
			return calculateVertical(dy);
		}
		if (dy == 0) {
			return calculateHorizontal(dx);
		}
		if (Math.abs(dx) == Math.abs(dy)) {
			return calculateDiagonal(dx, dy);
		}
		return calculateKnight(dx, dy);
	}

	private static Direction calculateVertical(final int dy) {
		if (dy < 0) {
			return DOWN;
		}
		return UP;
	}

	private static Direction calculateHorizontal(int dx) {
		if (dx < 0) {
			return LEFT;
		}
		return RIGHT;
	}

	private static Direction calculateDiagonal(final int dx, final int dy) {
		if (dx < 0 && dy > 0) {
			return LEFT_UP;
		}
		if (dx < 0 && dy < 0) {
			return LEFT_DOWN;
		}
		if (dx > 0 && dy > 0) {
			return RIGHT_UP;
		}
		return RIGHT_DOWN;
	}

	private static Direction calculateKnight(int dx, int dy) {
		final List<Direction> knightDirections = List.of(KNIGHT_RIGHT_UP, KNIGHT_RIGHT_DOWN, KNIGHT_LEFT_UP,
			KNIGHT_LEFT_DOWN, KNIGHT_UP_RIGHT, KNIGHT_UP_LEFT, KNIGHT_DOWN_RIGHT, KNIGHT_DOWN_LEFT);
		return knightDirections.stream()
			.filter(direction -> direction.dx == dx && direction.dy == dy)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
	}

	private static int diffFile(Position source, Position target) {
		return target.fileValue() - source.fileValue();
	}

	private static int diffRank(Position source, Position target) {
		return target.rankValue() - source.rankValue();
	}

	public int dx() {
		return dx;
	}

	public int dy() {
		return dy;
	}
}
