package chess.domain.piece;

import java.util.List;

import chess.domain.RelativePosition;

public enum Movement {

	KING(Mobility.ONCE, Direction.getAll()),
	QUEEN(Mobility.INFINITE, Direction.getAll()),
	ROOK(Mobility.INFINITE, Direction.getCross()),
	BISHOP(Mobility.INFINITE, Direction.getDiagonal()),
	KNIGHT(Mobility.ONCE, Direction.getLShaped()),
	PAWN(Mobility.ONCE, Direction.getNorthern()),
	EMPTY(Mobility.EMPTY, Direction.getEmpty());

	private final Mobility mobility;
	private final List<Direction> directions;

	Movement(final Mobility mobility, final List<Direction> directions) {
		this.mobility = mobility;
		this.directions = directions;
	}

	public boolean isMobile(final RelativePosition relativePosition) {
		RelativePosition unitPosition = relativePosition.toUnit();
		if (isMovedTooFar(relativePosition, unitPosition)) {
			return false;
		}
		return containsUnitPosition(unitPosition);
	}

	private boolean isMovedTooFar(final RelativePosition relativePosition, final RelativePosition unitPosition) {
		return mobility.equals(Mobility.ONCE) && !unitPosition.equals(relativePosition);
	}

	private boolean containsUnitPosition(final RelativePosition unitPosition) {
		return directions.stream()
			.anyMatch(direction -> direction.matches(unitPosition));
	}
}
