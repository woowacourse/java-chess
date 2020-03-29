package chess.domain.result;

import chess.domain.piece.*;

import java.util.Arrays;

public enum PieceType {
    WHITE_PAWN(WhitePawn.class, 1),
    BLACK_PAWN(BlackPawn.class, 1),
    KNIGHT(Knight.class, 2.5),
    BISHOP(Bishop.class, 3),
    ROOK(Rook.class, 5),
    QUEEN(Queen.class, 9),
    KING(King.class, 0);

    private static final String PIECE_TYPE_NOT_FOUND_EXCEPTION_MESSAGE = "해당하는 체스 말의 정보가 없습니다.";

    private final Object pieceClass;
    private final double score;

    PieceType(Object pieceClass, double score) {
        this.pieceClass = pieceClass;
        this.score = score;
    }

    public static double getScoreOf(Piece piece) {
        return Arrays.stream(values())
                .filter(type -> type.pieceClass.equals(piece.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PIECE_TYPE_NOT_FOUND_EXCEPTION_MESSAGE))
                .score;
    }
}
