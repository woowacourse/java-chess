package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected final List<Direction> directions;
    protected final Color color;

    protected Piece(final Color color) {
        directions = new ArrayList<>();
        this.color = color;
    }

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
}
