package chess;

import java.util.Arrays;
import java.util.List;

public enum Color {

    BLACK(new Position(Column.A, Row.SEVEN)),
    WHITE(new Position(Column.A, Row.ONE)),
    EMPTY(null);

    private final Position firstPawnPosition;

    Color(Position firstPawnPosition) {
        this.firstPawnPosition = firstPawnPosition;
    }

    public static List<Color> validColors() {
        return Arrays.stream(Color.values())
                .filter(color -> !color.isEmpty())
                .toList();
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public Color opposite() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            default -> EMPTY;
        };
    }

    public Position getFirstPawnPosition() {
        return firstPawnPosition;
    }
}
