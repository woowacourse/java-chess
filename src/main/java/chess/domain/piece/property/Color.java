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

    public String convertName(String name) {
        if (this == BLACK) {
            name = name.toUpperCase();
        }

        return name;
    }

    public Direction forward() {
        return forward;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }
}
