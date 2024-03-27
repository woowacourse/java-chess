package chess.view;

import java.util.Arrays;
import java.util.NoSuchElementException;
import chess.domain.piece.PieceType;

enum PieceShapeSelector {

    KING(PieceType.KING, "k"),
    QUEEN(PieceType.QUEEN, "q"),
    BISHOP(PieceType.BISHOP, "b"),
    KNIGHT(PieceType.KNIGHT, "n"),
    ROOK(PieceType.ROOK, "r"),
    PAWN(PieceType.PAWN, "p"),
    EMPTY(PieceType.EMPTY, ".");

    private final PieceType pieceType;
    private final String shape;

    PieceShapeSelector(PieceType pieceType, String shape) {
        this.pieceType = pieceType;
        this.shape = shape;
    }

    public static String selectShape(PieceType pieceType) {
        return Arrays.stream(PieceShapeSelector.values())
                .filter(it -> it.pieceType == pieceType)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 기물입니다."))
                .shape;
    }
}
