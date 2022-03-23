package chess;

import java.util.Locale;

public enum PieceType {
    EMPTY("."),
    PAWN("P"),
    KNIGHT("N"),
    ROOK("R"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }

    public String getName(PieceColor pieceColor) {
        if (pieceColor == PieceColor.WHITE) {
            return name.toLowerCase(Locale.ENGLISH);
        }
        return name;
    }
}
