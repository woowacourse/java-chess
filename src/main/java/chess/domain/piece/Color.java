package chess.domain.piece;


import java.util.Arrays;

public enum Color {
    WHITE("white"),
    BLACK("black");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public Color next() {
        if (this == Color.BLACK) return Color.WHITE;
        return Color.BLACK;
    }

    public static Color from(String color) {
        return Arrays.stream(Color.values())
                .filter(c -> c.color.equals(color))
                .findAny().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색입니다."));
    }

    public String getName() {
        return this.color;
    }
}
