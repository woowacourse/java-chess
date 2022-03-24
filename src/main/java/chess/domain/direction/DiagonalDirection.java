package chess.domain.direction;

import java.util.function.BiPredicate;

import chess.domain.Position;
import chess.domain.UnitPosition;

public enum DiagonalDirection implements Direction {

	NORTH_EAST(
		(rowDifference, columnDifference) ->
			rowDifference < 0 && columnDifference < 0 && rowDifference - columnDifference == 0,
		new UnitPosition(1, 1)
	),
	NORTH_WEST(
		(rowDifference, columnDifference) ->
			rowDifference < 0 && columnDifference > 0 && rowDifference + columnDifference == 0,
		new UnitPosition(1, -1)
	),
	SOUTH_EAST(
		(rowDifference, columnDifference) -> rowDifference > 0 && columnDifference < 0
			&& rowDifference + columnDifference == 0,
		new UnitPosition(-1, 1)
	),
	SOUTH_WEST(
		(rowDifference, columnDifference) ->
			rowDifference > 0 && columnDifference > 0 &&  rowDifference - columnDifference == 0,
		new UnitPosition(-1, -1));

	private final BiPredicate<Integer, Integer> directionPredicate;
	private final UnitPosition unitPosition;

	DiagonalDirection(
		BiPredicate<Integer, Integer> quadrantPredicate, UnitPosition unitPosition) {
		this.directionPredicate = quadrantPredicate;
		this.unitPosition = unitPosition;
	}

	@Override
	public boolean confirm(Position from, Position to) {
		return this.directionPredicate.test(from.subtractRow(to), from.subtractColumn(to));
	}

	@Override
	public UnitPosition getUnitPosition() {
		return unitPosition;
	}
}
