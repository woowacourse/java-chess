package chess.domain.position;

import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Set;

public enum PathStatus {
    OPEN,
    BLOCKED;

    public static PathStatus determine(final Set<Position> route, final Map<Position, Piece> board) {
        if (route.stream().anyMatch(board::containsKey)) {
            return BLOCKED;
        }
        return OPEN;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
