package chess.domain.piece.property;

import chess.domain.piece.position.Direction;

public enum Color {
    Black (Direction.Down),
    White (Direction.Up)
    ;

    private final Direction forward;

    Color(Direction forward) {
        this.forward = forward;
    }

    public String convertName(String name) {
        if (this == Black) {
            name = name.toUpperCase();
        }

        return name;
    }

    public Direction forward() {
        return forward;
    }
}
