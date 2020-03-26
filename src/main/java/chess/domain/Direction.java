package chess.domain;

import java.util.function.BiPredicate;

import chess.domain.position.Position;

public enum Direction {
	COL(Position::isSameCol),
	ROW(Position::isSameRow),
	POSITIVE_DIAGONAL(Position::isPositiveDiagonal),
	NEGATIVE_DIAGONAL(Position::isNegativeDiagonal),
	NON_LINEAR((p1, p2) -> false);

	private BiPredicate<Position, Position> filter;

	Direction(BiPredicate<Position, Position> filter) {
		this.filter = filter;
	}

	public boolean isSameDirection(Position source, Position target) {
		return filter.test(source, target);
	}
}
