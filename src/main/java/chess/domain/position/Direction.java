package chess.domain.position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {
	COL(Position::isSameCol),
	ROW(Position::isSameRow),
	POSITIVE_DIAGONAL(Position::isPositiveDiagonal),
	NEGATIVE_DIAGONAL(Position::isNegativeDiagonal),
	NON_LINEAR((source, target) -> false);

	private BiPredicate<Position, Position> filter;

	Direction(BiPredicate<Position, Position> filter) {
		this.filter = filter;
	}

	public static Direction findDirection(Position source, Position target) {
		return Arrays.stream(values())
				.filter(direction -> direction.isMatch(source, target))
				.findAny()
				.orElse(NON_LINEAR);
	}

	public boolean isMatch(Position source, Position target) {
		return filter.test(source, target);
	}
}
