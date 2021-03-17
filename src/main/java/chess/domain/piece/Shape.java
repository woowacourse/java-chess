package chess.domain.piece;

import chess.domain.board.Board;

import java.util.function.BiPredicate;

public enum Shape {
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K"),
    PAWN("P");

    private final String notation;

    Shape(final String notation) {
        this.notation = notation;
    }

    public String getNotation(final Color color) {
        if (color == Color.BLACK) {
            return notation.toUpperCase();
        }

        return notation.toLowerCase();
    }
}
