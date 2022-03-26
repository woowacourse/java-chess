package chess.domain.piece;

import chess.domain.Color;

public enum PieceType {

    KING("K", 0),
    QUEEN("Q", 9),
    ROOK("R", 5),
    BISHOP("B", 3),
    KNIGHT("N", 2.5),
    PAWN("P", 1),
    EMPTY(".", 0)
    ;

    private final String notation;
    private final double score;

    PieceType(String notation, double score) {
        this.notation = notation;
        this.score = score;
    }

    public String getNotation(Color color) {
        if (color.isBlack()) {
            return notation;
        }

        return notation.toLowerCase();
    }

    public double getScore() {
        return score;
    }
}
