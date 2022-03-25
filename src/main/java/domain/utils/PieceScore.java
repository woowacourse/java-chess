package domain.utils;

import java.util.Arrays;

public enum PieceScore {

    Queen(9, "Q"),
    Bishop(3, "B"),
    Knight(2.5, "N"),
    Rook(5, "R"),
    Pawn(1, "P"),
    King(0, "K");

    private static final double DUPLICATE_PAWN = 0.5;

    private double score;
    private String symbol;

    PieceScore(final double score, final String symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public static Double createScore(String symbol, boolean duplicatedPawn) {
        Double score =  Arrays.stream(PieceScore.values())
                .filter(pieceScore -> pieceScore.symbol.equals(symbol))
                .map(pieceScore -> pieceScore.score)
                .findFirst()
                .orElse(null);
        if(duplicatedPawn && score == Pawn.score){
            return DUPLICATE_PAWN;
        }
        return score;
    }

}
