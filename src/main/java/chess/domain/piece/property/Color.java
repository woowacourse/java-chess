package chess.domain.piece.property;

import chess.domain.piece.position.Direction;
import java.util.Arrays;
import java.util.NoSuchElementException;

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
                .orElseThrow(() -> new NoSuchElementException("해당하는 이름에 대응하는 색이 존재하지 않습니다."));
    }

    public Direction getForwardDirection() {
        return forward;
    }

    public boolean isSameColor(Color color) {
        return this == color;
    }
}
