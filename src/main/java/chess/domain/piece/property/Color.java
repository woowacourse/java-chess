package chess.domain.piece.property;

import chess.domain.piece.position.Direction;
import java.util.Arrays;

public enum Color {
    BLACK(Direction.DOWN),
    WHITE(Direction.UP)
    ;

    private final Direction forward;

    Color(Direction forward) {
        this.forward = forward;
    }

    public static Color of(String colorName) {
        return Arrays.stream(Color.values())
                .filter(color -> color.name().equals(colorName))
                .findFirst()
                .orElseThrow();
    }

    public Direction getForwardDirection() {
        return forward;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }
}
