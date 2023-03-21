package chess.domain.piece;

import java.util.List;

import chess.domain.RelativePosition;

public enum Movement {

	KING(Mobility.ONCE, Direction.getCrossAndDiagonal()),
	QUEEN(Mobility.INFINITE, Direction.getCrossAndDiagonal()),
	ROOK(Mobility.INFINITE, Direction.getCross()),
	BISHOP(Mobility.INFINITE, Direction.getDiagonal()),
	KNIGHT(Mobility.ONCE, Direction.getLShaped()),
	INITIAL_PAWN(Mobility.ONCE, Direction.getNorth()),
	PAWN(Mobility.ONCE, Direction.getNorthern()),
	EMPTY(Mobility.EMPTY, Direction.getEmpty());

	private final Mobility mobility;
	private final List<Direction> directions;

	Movement(final Mobility mobility, final List<Direction> directions) {
		this.mobility = mobility;
		this.directions = directions;
	}

	public boolean isMobile(final RelativePosition relativePosition) {
		RelativePosition unitPosition = relativePosition.getGcdDivided();
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
