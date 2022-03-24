package chess.domain.direction;

import java.util.function.BiPredicate;
import java.util.function.IntPredicate;

import chess.domain.Position;
import chess.domain.UnitPosition;

public enum BasicDirection implements Direction {

	SOUTH(row -> row > 0, column -> column == 0),
	NORTH(row -> row < 0, column -> column == 0),
	WEST(row -> row == 0, column -> column > 0),
	EAST(row -> row == 0, column -> column < 0);

	private final IntPredicate rowPredicate;
	private final IntPredicate columnPredicate;

	BasicDirection(BiPredicate<Integer, Integer> directionPredicate, UnitPosition unitPosition) {
		this.directionPredicate = directionPredicate;
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
