package chess.domain.piece.constant;

import java.util.Arrays;

import chess.domain.piece.Piece;
import chess.domain.piece.movable.multiple.Bishop;
import chess.domain.piece.movable.multiple.Queen;
import chess.domain.piece.movable.multiple.Rook;
import chess.domain.piece.movable.single.Knight;

public enum PromotablePiece {

    QUEEN(PieceType.QUEEN, Queen.getInstance()),
    BISHOP(PieceType.BISHOP, Bishop.getInstance()),
    ROOK(PieceType.ROOK, Rook.getInstance()),
    KNIGHT(PieceType.KNIGHT, Knight.getInstance()),
    ;

    private final PieceType pieceType;
    private final Piece piece;

    PromotablePiece(final PieceType pieceType, final Piece piece) {
        this.pieceType = pieceType;
        this.piece = piece;
    }

    public static Piece convertToPromotablePiece(final String pieceName) {
        return Arrays.stream(values())
                .filter(it -> equalsPieceName(it.pieceType, pieceName))
                .map(it -> it.piece)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("폰으로 프로모션할 수 있는 기물의 이름이 아닙니다."));
    }

    private static boolean equalsPieceName(final PieceType pieceType, final String pieceName) {
        return pieceName.equalsIgnoreCase(pieceType.getPieceName());
    }
}
