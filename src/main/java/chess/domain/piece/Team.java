package chess.domain.piece;

import static chess.domain.position.Direction.DOWN;
import static chess.domain.position.Direction.UP;

import chess.domain.position.Direction;

public enum Team {
    WHITE(UP),
    BLACK(DOWN);

    private final Direction direction;

    Team(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
