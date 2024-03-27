package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public static Color convertToColor(String colorName) {
        return Arrays.stream(values()).filter(start -> start.name().equals(colorName.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 Color 값입니다."));
    }

    public Color convertTurn() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        if (this.equals(WHITE)) {
            return BLACK;
        }
        throw new UnsupportedOperationException("적절하지 않은 턴 전환입니다.");
    }
}
