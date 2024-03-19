package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum pieceMapper {

    PAWN(PieceType.PAWN, "p"),
    ROOK(PieceType.ROOK, "r"),
    KNIGHT(PieceType.KNIGHT, "n"),
    BISHOP(PieceType.BISHOP, "b"),
    QUEEN(PieceType.QUEEN, "q"),
    KING(PieceType.KING, "k");

    private final PieceType pieceType;
    private final String value;

    pieceMapper(PieceType pieceType, String value) {
        this.pieceType = pieceType;
        this.value = value;
    }

    public static String findByPiece(Piece piece) {
        String value = Arrays.stream(values())
                .filter(pieceMapper -> piece.isSameType(pieceMapper.pieceType))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 기물이 없습니다."))
                .value;
        if (piece.isBlack()) {
            return value.toUpperCase();
        }
        return value;
    }
}
