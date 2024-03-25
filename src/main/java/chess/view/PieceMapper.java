package chess.view;

import chess.domain.Piece;
import chess.domain.PieceType;
import java.util.Arrays;

public enum PieceMapper {

    BLACK_PAWN(PieceType.BLACK_PAWN, "P"),
    WHITE_PAWN(PieceType.WHITE_PAWN, "p"),
    ROOK(PieceType.ROOK, "r"),
    KNIGHT(PieceType.KNIGHT, "n"),
    BISHOP(PieceType.BISHOP, "b"),
    QUEEN(PieceType.QUEEN, "q"),
    KING(PieceType.KING, "k");

    private final PieceType pieceType;
    private final String value;

    PieceMapper(PieceType pieceType, String value) {
        this.pieceType = pieceType;
        this.value = value;
    }

    public static String findByPieceType(Piece piece) {
        String value = Arrays.stream(values())
                .filter(pieceMapper -> piece.getPieceType() == pieceMapper.pieceType)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 기물이 없습니다."))
                .value;
        if (piece.isBlack()) {
            return value.toUpperCase();
        }
        return value;
    }
}
