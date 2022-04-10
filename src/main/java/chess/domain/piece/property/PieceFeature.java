package chess.domain.piece.property;

import java.util.Arrays;

public enum PieceFeature {

    QUEEN("Q", 9),
    BISHOP("B", 3),
    KNIGHT("N", 2.5),
    ROOK("R", 5),
    PAWN("P", 1),
    KING("K", 0);

    private static final double DUPLICATE_PAWN = 0.5;

    private String symbol;
    private double score;

    PieceFeature(final String symbol, final double score) {
        this.symbol = symbol;
        this.score = score;
    }

    public String symbol(final Team team) {
        if (team == Team.BLACK) {
            return symbol;
        }
        return symbol.toLowerCase();
    }

    public static Double createScore(final String symbol, final boolean duplicatedPawn) {
        Double score = Arrays.stream(PieceFeature.values())
                .filter(pieceFeature -> pieceFeature.symbol.equals(symbol))
                .map(pieceFeature -> pieceFeature.score)
                .findFirst()
                .orElse(0d);
        if (duplicatedPawn && score == PAWN.score) {
            return DUPLICATE_PAWN;
        }
        return score;
    }

    public String symbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "PieceFeature{" +
                "symbol='" + symbol + '\'' +
                ", score=" + score +
                '}';
    }
}
