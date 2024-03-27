package chess.domain.chessGame;

import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum Score {
    QUEEN(PieceType.QUEEN, 9),
    ROOK(PieceType.ROOK, 5),
    BISHOP(PieceType.BISHOP, 3),
    KNIGHT(PieceType.KNIGHT, 2.5),
    KING(PieceType.KING, 0),
    PAWN(PieceType.PAWN, 1);

    private final PieceType pieceType;
    private final double score;

    Score(final PieceType pieceType, final double score) {
        this.pieceType = pieceType;
        this.score = score;
    }

    static double getScoreByPieceType(final PieceType pieceType) {
        return Arrays.stream(values())
                .filter(value -> value.pieceType == pieceType)
                .findFirst()
                .map(value -> value.score)
                .orElse((double) 0);
    }
}
