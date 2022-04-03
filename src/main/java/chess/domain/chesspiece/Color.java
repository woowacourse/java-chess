package chess.domain.chesspiece;

import java.util.Arrays;

public enum Color {

    WHITE("white"),
    BLACK("black");

    private final String value;

    Color(final String value) {
        this.value = value;
    }

    public static Color from(String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("색깔을 찾을 수 없습니다."));
    }

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public Color toOpposite() {
        if (isBlack()) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
