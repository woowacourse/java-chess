package chess.domain.game;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK;

    public static Color from(String color) {
        return Arrays.stream(Color.values())
                .filter(colorType -> colorType.name().equals(color))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색깔입니다."));
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public String convertByColor(String name) {
        if (isBlack()) {
            return name.toUpperCase();
        }
        return name.toLowerCase();
    }

    public Color toOpposite() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

}
