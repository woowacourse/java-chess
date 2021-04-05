package chess.domain.feature;

import java.util.Arrays;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    NO_COLOR("blank");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public boolean isWhite() {
        return this.equals(WHITE);
    }

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public Color getOppositeColor() {
        if (this.isBlack()) {
            return Color.WHITE;
        }
        if (this.isWhite()) {
            return Color.BLACK;
        }
        throw new IllegalArgumentException();
    }

    public static Color convert(String color) {
        return Arrays.stream(values())
                .filter(value -> value.color.equals(color))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getColor() {
        return color;
    }
}
