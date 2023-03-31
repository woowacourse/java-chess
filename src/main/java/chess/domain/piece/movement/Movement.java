package chess.domain.piece.movement;

import chess.domain.position.RelativePosition;

import java.util.List;

public enum Movement {

    KING(Mobility.ONCE, Direction.getCrossAndDiagonal()),
    QUEEN(Mobility.INFINITE, Direction.getCrossAndDiagonal()),
    ROOK(Mobility.INFINITE, Direction.getCross()),
    BISHOP(Mobility.INFINITE, Direction.getDiagonal()),
    KNIGHT(Mobility.ONCE, Direction.getLShaped()),
    PAWN(Mobility.ONCE, Direction.getNorthern());

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
        return mobility == Mobility.ONCE && !unitPosition.equals(relativePosition);
    }

    private boolean containsUnitPosition(final RelativePosition unitPosition) {
        return directions.stream()
                .anyMatch(direction -> direction.matches(unitPosition));
    }
}
