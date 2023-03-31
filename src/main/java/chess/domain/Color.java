package chess.domain;

import chess.domain.position.File;
import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    Color() {
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }
}
