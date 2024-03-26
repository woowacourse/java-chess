package chess.model.material;

import chess.model.piece.Piece;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public static Color findColor(Piece piece) {
        if (piece.isSameColor(WHITE)) {
            return WHITE;
        }
        if (piece.isSameColor(BLACK)) {
            return BLACK;
        }
        return NONE;
    }

    public Color rotate() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }

    public boolean isSame(Color color) {
        return this == color;
    }
}
