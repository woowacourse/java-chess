package techcourse.fp.chess.domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;



    public static Color createByName(String name) {
        return Arrays.stream(values())
                .filter(color -> color.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Color를 입력하셨습니다."));
    }

    public boolean isSameColor(final Color color) {
        return this == color;
    }

    public boolean isOpponent(final Color color) {
        return this == BLACK && color == WHITE || this == WHITE && color == BLACK;
    }

    public Color getOtherSide() {
        if (this == BLACK) {
            return WHITE;
        }

        if (this == WHITE) {
            return BLACK;
        }

        throw new IllegalStateException("백이나 흑이 아닙니다.");
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
