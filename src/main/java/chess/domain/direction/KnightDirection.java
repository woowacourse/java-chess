package chess.domain.direction;

import java.util.function.BiPredicate;

import chess.domain.Position;

public enum KnightDirection implements Direction {
	NORTH_NORTH_EAST(
		(rowDifference, columnDifference) -> rowDifference == -2 && columnDifference == -1
	),
	EAST_NORTH_EAST(
		(rowDifference, columnDifference) -> rowDifference == -1 && columnDifference == -2
	),
	EAST_SOUTH_EAST(
		(rowDifference, columnDifference) -> rowDifference == 1 && columnDifference == -2
	),
	SOUTH_SOUTH_EAST(
		(rowDifference, columnDifference) -> rowDifference == 2 && columnDifference == -1
	),
	SOUTH_SOUTH_WEST(
		(rowDifference, columnDifference) -> rowDifference == 2 && columnDifference == 1
	),
	WEST_SOUTH_WEST(
		(rowDifference, columnDifference) -> rowDifference == 1 && columnDifference == 2
	),
	WEST_NORTH_WEST(
		(rowDifference, columnDifference) -> rowDifference == -1 && columnDifference == 2
	),
	NORTH_NORTH_WEST(
		(rowDifference, columnDifference) -> rowDifference == -2 && columnDifference == 1
	);

	private final BiPredicate<Integer, Integer> positionPredicate;

	KnightDirection(BiPredicate<Integer, Integer> positionPredicate) {
		this.positionPredicate = positionPredicate;
	}

	@Override
	public boolean confirm(Position from, Position to) {
		return this.positionPredicate.test(from.subtractRow(to), from.subtractColumn(to));
	}
}
