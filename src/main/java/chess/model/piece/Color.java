package chess.model.piece;

import java.util.Arrays;

public enum Color {
    WHITE,
    NONE,
    BLACK;

    public Color getOpposite() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }

    public static Color from(String colorName) {
        return Arrays.stream(values())
                .filter(color -> color.name().equals(colorName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 색상입니다."));
    }
}
