package chess.domain.direction;

import chess.domain.Position;
import chess.domain.UnitPosition;

public enum KnightDirection implements Direction {
	NORTH_NORTH_EAST(new UnitPosition(2, 1)),
	EAST_NORTH_EAST(new UnitPosition(1, 2)),
	EAST_SOUTH_EAST(new UnitPosition(-1, 2)),
	SOUTH_SOUTH_EAST(new UnitPosition(-2, 1)),
	SOUTH_SOUTH_WEST(new UnitPosition(-2, -1)),
	WEST_SOUTH_WEST(new UnitPosition(-1, -2)),
	WEST_NORTH_WEST(new UnitPosition(1, -2)),
	NORTH_NORTH_WEST(new UnitPosition(2, -1));

	private final UnitPosition unitPosition;

	KnightDirection(UnitPosition unitPosition) {
		this.unitPosition = unitPosition;
	}

	@Override
	public boolean confirm(Position from, Position to) {
		return from.subtractRow(to) + unitPosition.getUnitRow() == 0
			&& from.subtractColumn(to) + unitPosition.getUnitColumn() == 0;
	}

	@Override
	public boolean isDiagonal() {
		return false;
	}

	@Override
	public UnitPosition getUnitPosition() {
		return unitPosition;
	}
}
