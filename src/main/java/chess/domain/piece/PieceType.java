package chess.domain.piece;

import chess.domain.movement.Movement;

import java.util.Collections;
import java.util.List;

import static chess.domain.movement.Movement.*;

public enum PieceType {
    KING(List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL)),
    QUEEN(List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL,
            CONTINUOUS_STRAIGHT, CONTINUOUS_DIAGONAL)),
    ROOK(List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT)),
    KNIGHT(List.of(CONTINUOUS_L_SHAPE)),
    BISHOP(List.of(CONTINUOUS_DIAGONAL, DISCONTINUOUS_DIAGONAL)),
    PAWN(List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL)),
    EMPTY(Collections.emptyList());

    private final List<Movement> movements;

    PieceType(final List<Movement> movements) {
        this.movements = movements;
    }

    public List<Movement> getMovements() {
        return movements;
    }
}
