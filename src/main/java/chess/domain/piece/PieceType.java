package chess.domain.piece;

import chess.domain.movement.Movement;

import java.util.Collections;
import java.util.List;

import static chess.domain.movement.Movement.*;

public enum PieceType {
    KING("k", List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL)),
    QUEEN("q", List.of(DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL,
            CONTINUOUS_STRAIGHT, CONTINUOUS_DIAGONAL)),
    ROOK("r", List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT)),
    KNIGHT("n", List.of(CONTINUOUS_L_SHAPE)),
    BISHOP("b", List.of(CONTINUOUS_DIAGONAL, DISCONTINUOUS_DIAGONAL)),
    PAWN("p", List.of(CONTINUOUS_STRAIGHT, DISCONTINUOUS_STRAIGHT, DISCONTINUOUS_DIAGONAL)),
    EMPTY(".", Collections.emptyList());

    private final String name;
    private final List<Movement> movements;

    PieceType(final String name, final List<Movement> movements) {
        this.name = name;
        this.movements = movements;
    }

    public String getName() {
        return name;
    }

    public List<Movement> getMovements() {
        return movements;
    }
}
