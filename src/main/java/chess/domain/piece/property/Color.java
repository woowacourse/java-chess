package chess.domain.piece.property;

import chess.domain.piece.position.Direction;

public enum Color {
    BLACK(Direction.DOWN),
    WHITE(Direction.UP)
    ;

    private final Direction forward;

    Color(Direction forward) {
        this.forward = forward;
    }

    public Direction getForwardDirection() {
        return forward;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }
}
