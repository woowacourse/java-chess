package chess.domain.piece.property;

import chess.domain.game.state.position.Direction;

public enum Color {
    Black (Direction.Down),
    White (Direction.Up)
    ;

    private final Direction forward;

    Color(Direction forward) {
        this.forward = forward;
    }

    public Direction forward() {
        return forward;
    }
}
