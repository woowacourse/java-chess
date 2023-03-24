package chess.domain.piece;

import chess.domain.movement.Movement;

import java.util.Collections;
import java.util.List;

import static chess.domain.movement.Movement.*;

public enum PieceType {
    KING(0, List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL)),
    QUEEN(9, List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL,
            CONTINUOUS_STRAIGHT, CONTINUOUS_DIAGONAL)),
    ROOK(5, List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT)),
    KNIGHT(2.5, List.of(CONTINUOUS_L_SHAPE)),
    BISHOP(3, List.of(CONTINUOUS_DIAGONAL, DISCONTINUOUS_DIAGONAL)),
    PAWN(1, List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL)),
    EMPTY(0, Collections.emptyList());

    private final double score;
    private final List<Movement> movements;

    PieceType(final double score, final List<Movement> movements) {
        this.score = score;
        this.movements = movements;
    }

    public double getScore() {
        return score;
    }

    public List<Movement> getMovements() {
        return movements;
    }
}
