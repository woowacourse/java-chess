package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Map;
import java.util.Set;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract Set<Position> computePath(Position source, Position target);

    protected void validateSamePosition(Position source, Position target) {
        if (source == target) {
            throw new IllegalArgumentException();
        }
    }

    public abstract boolean canMove(Map<Position, Boolean> isExists, Position source, Position target);

    public boolean isEmpty() {
        return false;
    }
}
