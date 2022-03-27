package utils;

import java.util.Arrays;

public enum PieceScore {
    Queen(9, "Q"),
    Bishop(3, "B"),
    Knight(2.5, "N"),
    Rook(5, "R"),
    Pawn(1, "P"),
    King(0, "K");

    public static final double DUPLICATE_PAWN = 0.5;

    private final double score;
    private final String symbol;

    PieceScore(final double score, final String symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public static PieceScore of(String symbol) {
        return Arrays.stream(PieceScore.values())
            .filter(pieceScore -> pieceScore.symbol.equals(symbol))
            .findFirst()
            .orElse(null);
    }

    public double score() {
        return this.score;
    }
}
