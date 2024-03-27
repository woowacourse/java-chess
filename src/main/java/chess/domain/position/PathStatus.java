package chess.domain.position;

import chess.domain.piece.Piece;

import java.util.Map;

public enum PathStatus {
    OPEN,
    BLOCKED;

    public static PathStatus determine(final Movement movement, final Map<Position, Piece> board) {
        if (movement.findRoute().stream().anyMatch(board::containsKey)) {
            return BLOCKED;
        }
        return OPEN;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
