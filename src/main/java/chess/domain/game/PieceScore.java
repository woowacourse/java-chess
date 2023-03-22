package chess.domain.game;

import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceScore {

    KING(PieceType.KING, 0d),
    QUEEN(PieceType.QUEEN, 9d),
    KNIGHT(PieceType.KNIGHT, 2.5d),
    ROOK(PieceType.ROOK, 5d),
    BISHOP(PieceType.BISHOP, 3d),
    PAWN(PieceType.PAWN, 1d);

    private final PieceType pieceType;
    private final double score;

    PieceScore(PieceType pieceType, double score) {
        this.pieceType = pieceType;
        this.score = score;
    }

    public static double getScore(PieceType pieceType) {
        return Arrays.stream(PieceScore.values())
                .filter(pieceScore -> pieceScore.pieceType == pieceType)
                .mapToDouble(pieceScore -> pieceScore.score)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("하이하이"));
    }
}
