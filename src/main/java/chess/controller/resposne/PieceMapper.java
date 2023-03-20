package chess.controller.resposne;

import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceMapper {
    PAWN(PieceType.PAWN, "p"),
    ROOK(PieceType.ROOK, "r"),
    KNIGHT(PieceType.KNIGHT, "n"),
    BISHOP(PieceType.BISHOP, "b"),
    QUEEN(PieceType.QUEEN, "q"),
    KING(PieceType.KING, "k"),
    EMPTY(PieceType.EMPTY, ".");

    private final PieceType pieceType;
    private final String pieceName;

    PieceMapper(PieceType pieceType, String pieceName) {
        this.pieceType = pieceType;
        this.pieceName = pieceName;
    }

    public static String getPieceName(PieceType pieceType) {
        return Arrays.stream(values())
                .filter(it -> it.pieceType == pieceType)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"))
                .pieceName;
    }
}
