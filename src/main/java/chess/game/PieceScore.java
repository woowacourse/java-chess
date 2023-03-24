package chess.game;

import java.util.Arrays;

import chess.piece.PieceType;

public enum PieceScore {

    PAWN(1, PieceType.PAWN),
    KNIGHT(2.5, PieceType.KNIGHT),
    BISHOP(3, PieceType.BISHOP),
    ROOK(5, PieceType.ROOK),
    QUEEN(9, PieceType.QUEEN),
    ;

    private final double score;
    private final PieceType type;

    PieceScore(final double score, final PieceType type) {
        this.score = score;
        this.type = type;
    }

    public static double from(final PieceType pieceType) {
        return Arrays.stream(values())
                .filter(pieceScore -> pieceScore.type == pieceType)
                .mapToDouble(pieceScore -> pieceScore.score)
                .sum();
    }
}
