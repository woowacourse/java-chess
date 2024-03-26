package chess.domain.piece;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    protected final Set<Direction> directions;
    protected final Color color;

    protected Piece(final Color color) {
        directions = new HashSet<>();
        this.color = color;
    }

    abstract public PieceType getOwnPieceType();

    abstract public boolean canMoveMoreThenOnce();

    abstract public boolean isEmpty();

    final public boolean canMoveInTargetDirection(final Direction targetDirection) {
        return directions.contains(targetDirection);
    }

    final public boolean isPawn() {
        return getOwnPieceType().name().equals(PieceType.PAWN.name());
    }

    final public boolean isAlly(final Piece piece) {
        if (piece.isEmpty()) {
            return false;
        }

        return this.color == piece.color;
    }

    final public boolean isBlack() {
        return this.color == Color.BLACK;
    }
}
