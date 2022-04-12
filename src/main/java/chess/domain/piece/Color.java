package chess.domain.piece;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;

    private static final String NO_MATCHED_COLOR_FOUND_BY_PIECE = "해당 기물의 색상을 확인하는 데 실패하였습니다";

    public static Color from(Piece piece) {
        return Arrays.stream(values())
                .filter(piece::isSameColor)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NO_MATCHED_COLOR_FOUND_BY_PIECE));
    }

    public Color opposite() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }

        return EMPTY;
    }

    public static Color fromInt(int number) {
        if (number == 0) {
            return WHITE;
        }
        return BLACK;
    }
}
