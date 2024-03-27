package chess.view;

import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.NoSuchElementException;

enum PieceShape {

    KING(PieceType.KING, "k"),
    QUEEN(PieceType.QUEEN, "q"),
    BISHOP(PieceType.BISHOP, "b"),
    KNIGHT(PieceType.KNIGHT, "n"),
    ROOK(PieceType.ROOK, "r"),
    PAWN(PieceType.PAWN, "p"),
    EMPTY(PieceType.EMPTY, ".");;

    private final PieceType pieceType;
    private final String shape;

    PieceShape(PieceType pieceType, String shape) {
        this.pieceType = pieceType;
        this.shape = shape;
    }

    public static PieceShape valueOf(PieceType pieceType) {
        return Arrays.stream(PieceShape.values())
                .filter(it -> it.pieceType == pieceType)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 기물입니다."));
    }

    public String getShape() {
        return shape;
    }
}
