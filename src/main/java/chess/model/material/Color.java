package chess.model.material;

import chess.model.piece.Piece;

public enum Color {
    WHITE(0),
    BLACK(1),
    NONE(2);

    private static final int COLOR_SIZE = 2;

    private final int id;

    Color(int id) {
        this.id = id;
    }

    public static Color findColor(Piece piece) {
        if (piece.isSameColor(WHITE)) {
            return WHITE;
        }
        if (piece.isSameColor(BLACK)) {
            return BLACK;
        }
        return NONE;
    }

    public boolean isDifferentColor(int turnCount) {
        return !isSameColor(turnCount);
    }

    public boolean isSameColor(int turnCount) {
        int turnColorId = turnCount % COLOR_SIZE;
        return this.id == turnColorId;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
