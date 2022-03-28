package chess.domain.position;

public enum UnitDirection {
	NORTH(new Direction(0, 1)),
	EAST(new Direction(1, 0)),
	WEST(new Direction(-1, 0)),
	SOUTH(new Direction(0, -1)),
	NORTH_WEST(new Direction(-1, 1)),
	NORTH_EAST(new Direction(1, 1)),
	SOUTH_WEST(new Direction(-1, -1)),
	SOUTH_EAST(new Direction(1, -1)),
	NNW(new Direction(-1, 2)),
	NNE(new Direction(1, 2)),
	EEN(new Direction(2, 1)),
	EES(new Direction(2, -1)),
	SSE(new Direction(1, -2)),
	SSW(new Direction(-1, -2)),
	WWN(new Direction(-2, 1)),
	WWS(new Direction(-2, -1)),
	PAWN_START_MOVE(new Direction(0, 2));

	private final Direction unitDirection;

	UnitDirection(Direction unitDirection) {
		this.unitDirection = unitDirection;
	}

	public Direction getUnitDirection() {
		return unitDirection;
	}
}
