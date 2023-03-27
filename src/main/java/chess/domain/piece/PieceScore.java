package chess.domain.piece;

import java.util.Arrays;
import java.util.Map;

public enum PieceScore {

    QUEEN(Queen.class, 9.0, 0),
    ROOK(Rook.class, 5.0, 0),
    BISHOP(Bishop.class, 3.0, 0),
    KNIGHT(Knight.class, 2.5, 0),
    PAWN(Pawn.class, 1.0, 0.5),
    KING(King.class, 0.0, 0);

    private final Class pieceType;
    private final double score;
    private final double extraValue;

    PieceScore(Class pieceType, double score, double extraValue) {
        this.pieceType = pieceType;
        this.score = score;
        this.extraValue = extraValue;
    }

    public static double calculateWithoutPawn(final Map<Class, Integer> numberOfPieces) {
        double total = 0;
        for (PieceScore pieceScore : PieceScore.values()) {
            total += numberOfPieces.get(pieceScore.pieceType) *  pieceScore.score;
        }
        return total;
    }

    public static double calculatePawn(final int defaultPawnCount, final int extraPawnCount) {
        return ((defaultPawnCount - extraPawnCount) * PAWN.score) + (extraPawnCount * PAWN.extraValue);
    }
}
