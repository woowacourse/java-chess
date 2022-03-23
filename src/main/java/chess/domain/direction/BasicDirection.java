package chess.domain.direction;

import java.util.function.IntPredicate;

import chess.domain.Position;

public enum BasicDirection implements Direction {

	SOUTH(row -> row > 0, column -> column == 0),
	NORTH(row -> row < 0, column -> column == 0),
	WEST(row -> row == 0, column -> column > 0),
	EAST(row -> row == 0, column -> column < 0);

	private final IntPredicate rowPredicate;
	private final IntPredicate columnPredicate;

	BasicDirection(IntPredicate rowPredicate, IntPredicate columnPredicate) {
		this.rowPredicate = rowPredicate;
		this.columnPredicate = columnPredicate;
	}

	@Override
	public boolean confirm(Position from, Position to) {
		return this.rowPredicate.test(from.subtractRow(to))
			&& this.columnPredicate.test(from.subtractColumn(to));
	}
}
