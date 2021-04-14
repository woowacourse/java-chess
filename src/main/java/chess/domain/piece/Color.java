package chess.domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK,
    WHITE,
    NONE;

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

    public static Color of(char name) {
        if (Character.isLowerCase(name)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public static Color of(String name) {
        return Arrays.stream(values())
            .filter(value -> value.name().equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("매칭되는 컬러가 없습니다."));
    }

    @Override
    public String toString() {
        return this.name();
    }
}
