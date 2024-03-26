package chess.model.material;

import chess.model.piece.Piece;
import java.util.Arrays;
import java.util.List;

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

    public static List<Color> allColors() {
        return Arrays.stream(values())
            .filter(color -> color != NONE)
            .toList();
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
