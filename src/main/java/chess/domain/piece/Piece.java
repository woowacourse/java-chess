package chess.domain.piece;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    protected final Set<Direction> directions = new HashSet<>();
    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    abstract public boolean canMoveMoreThenOnce();

    abstract public boolean isPawn();

    final public boolean canMoveInTargetDirection(final Direction targetDirection) {
        return directions.contains(targetDirection);
    }

    final public boolean isAlly(Piece piece) {
        return this.color == piece.color;
    }

    final public boolean isEnemy(Piece piece) {
        if (this.color.isBlack()) {
            return piece.color.isWhite();
        }
        if (this.color.isWhite()) {
            return piece.color.isBlack();
        }
        return false;
    }
}
