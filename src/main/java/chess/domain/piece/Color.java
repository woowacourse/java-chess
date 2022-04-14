package chess.domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK, WHITE, NONE;

    private static final String NOT_EXIST_COLOR = "[ERROR] 없는 색 입니다.";

    public static Color get(String colorName) {
        return Arrays.stream(values())
            .filter(value -> value.name().equals(colorName))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_COLOR));
    }
}
