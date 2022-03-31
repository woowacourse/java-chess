package domain.piece;

import java.util.Arrays;

public enum PieceScore {
    QUEEN(9, "Q"),
    BISHOP(3, "B"),
    KNIGHT(2.5, "N"),
    ROOK(5, "R"),
    PAWN(1, "P"),
    KING(0, "K");

    public static final double SEVERAL_PAWN_POINT = 0.5;

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

    public double score(boolean isSeveralPawn) {
        if (this == PAWN && isSeveralPawn) {
            return SEVERAL_PAWN_POINT;
        }
        return this.score;
    }
}
