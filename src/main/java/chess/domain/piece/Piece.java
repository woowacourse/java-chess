package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {

    private final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract List<Position> computePath(Position source, Position target);

    public abstract boolean canMove(Map<Position, Boolean> isExists, Position source, Position target);

    public boolean isEmpty() {
        return false;
    }
}
