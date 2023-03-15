package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract Set<Position> computePath(Position source, Position target);

    protected void validateSamePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException();
        }
    }

    public boolean canMove(Map<Position, Boolean> isEmptyPosition, Position source, Position target) {
        HashMap<Position, Boolean> defensiveCopied = new HashMap<>(isEmptyPosition);
        defensiveCopied.remove(target);
        return defensiveCopied.keySet()
                .stream()
                .allMatch(defensiveCopied::get);
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean equalsColor(final Piece targetSquare) {
        return color == targetSquare.color;
    }
}
