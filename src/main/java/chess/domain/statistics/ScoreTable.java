package chess.domain.statistics;

import chess.domain.piece.*;
import chess.domain.piece.RealPiece;

import java.util.Arrays;

public enum ScoreTable {
    KING(King.class, 0),
    QUEEN(Queen.class, 9),
    BISHOP(Bishop.class, 3),
    KNIGHT(Knight.class, 2.5),
    ROOK(Rook.class, 5),
    PAWN(Pawn.class, 1);

    private static final double PAWN_DISADVANTAGE_RATIO = 0.5;

    private final Class<? extends RealPiece> pieceClass;
    private final double score;

    ScoreTable(Class<? extends RealPiece> pieceClass, double score) {
        this.pieceClass = pieceClass;
        this.score = score;
    }

    private Class<? extends RealPiece> getPieceClass() {
        return pieceClass;
    }

    public static double getPawnDisadvantageRatio() {
        return PAWN_DISADVANTAGE_RATIO;
    }

    public static double convertToScore(RealPiece realPiece) {
        return Arrays.stream(values())
                .filter(item -> item.getPieceClass().equals(realPiece.getClass()))
                .mapToDouble(item -> item.score)
                .findAny()
                .getAsDouble();
    }
}
