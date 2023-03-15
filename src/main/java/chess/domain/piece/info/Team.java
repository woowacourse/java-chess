package chess.domain.piece.info;

import chess.domain.position.File;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public static Team initialOf(File file) {
        if (file == File.SEVEN || file == File.EIGHT) {
            return BLACK;
        }
        return WHITE;
    }

    public Team enemy() {
        if (this == EMPTY) {
            throw new UnsupportedOperationException("기물이 비어있습니다.");
        }
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
