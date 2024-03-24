package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.RowPosition;

import static chess.domain.position.Direction.N;
import static chess.domain.position.Direction.S;

public enum Team {
    WHITE(N, RowPosition.SIX),
    BLACK(S, RowPosition.ONE);

    private final Direction direction;
    private final RowPosition initialPawnRow;

    Team(Direction direction, RowPosition initialPawnRow) {
        this.direction = direction;
        this.initialPawnRow = initialPawnRow;
    }

    public Direction getDirection() {
        return direction;
    }

    public RowPosition getInitialPawnRow() {
        return initialPawnRow;
    }
}
