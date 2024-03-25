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

    final public boolean canMoveInTargetDirection(final Direction targetDirection) {
        return directions.contains(targetDirection);
    }

    final public boolean isAlly(Piece piece) {
        if (piece == null) {
            return false;
        }

        return this.color == piece.color;
    }

    final public boolean isBlack() {
        return this.color == Color.BLACK;
    }
}
