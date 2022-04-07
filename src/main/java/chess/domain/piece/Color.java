package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Color {

    WHITE, BLACK, NONE;

    public static Color convertColorByString(String color) {
        return Arrays.stream(values())
                .filter(value -> value.toString().equals(color.toUpperCase()))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public Color toggle() {
        validateColor();
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    private void validateColor() {
        if (this == NONE) {
            throw new IllegalArgumentException("[ERROR] 색상이 없습니다.");
        }
    }
}
