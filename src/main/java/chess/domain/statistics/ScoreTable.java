package chess.domain.statistics;

import chess.domain.piece.*;

import java.util.Arrays;

public enum ScoreTable {
    KING(King.class, 0),
    QUEEN(Queen.class, 9),
    BISHOP(Bishop.class, 3),
    KNIGHT(Knight.class, 2.5),
    ROOK(Rook.class, 5),
    PAWN(Pawn.class, 1),
    BLANK(Blank.class, 0);

    private static final double PAWN_DISADVANTAGE_RATIO = 0.5;

    private final Class<? extends Piece> pieceClass;
    private final double score;

    ScoreTable(Class<? extends Piece> pieceClass, double score) {
        this.pieceClass = pieceClass;
        this.score = score;
    }

    private Class<? extends Piece> getPieceClass() {
        return pieceClass;
    }

    public static double getPawnDisadvantageRatio() {
        return PAWN_DISADVANTAGE_RATIO;
    }

    public static double convertToScore(Piece piece) {
        return Arrays.stream(values())
                .filter(item -> item.getPieceClass().equals(piece.getClass()))
                .mapToDouble(item -> item.score)
                .findAny()
                .getAsDouble();
    }
}
