package chess.domain.pieces;

import java.util.Arrays;

public enum Color {

    BLACK("검은말"),
    WHITE("흰말"),
    ;

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public static Color opposite(final Color color) {
        if (color.isWhite()) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public static Color findColor(String colorName) {
        return Arrays.stream(values())
                .filter(color -> color.name().equals(colorName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 팀 명입니다."));
    }

    public String value() {
        return value;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
