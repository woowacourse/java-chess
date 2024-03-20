package chess.view;

import java.util.Arrays;
import java.util.Objects;

public enum PieceView {
    KING('K'),
    QUEEN('Q'),
    PAWN('P'),
    ROOK('R'),
    BISHOP('B'),
    KNIGHT('N');

    private final char display;

    PieceView(final char display) {
        this.display = display;
    }

    public static char getDisplayOf(final String type, final String color) {
        return Arrays.stream(PieceView.values())
                .filter(value -> value.name().equals(type))
                .map(value -> value.getDisplayOf(color))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public char getDisplayOf(final String color) {
        if (Objects.equals(color, "WHITE")) {
            return Character.toLowerCase(display);
        }
        return display;
    }
}
