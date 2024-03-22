package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.RowPosition;

import static chess.domain.position.Direction.DOWN;
import static chess.domain.position.Direction.UP;

public enum Team {
    WHITE(UP, RowPosition.SIX),
    BLACK(DOWN, RowPosition.ONE);

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
