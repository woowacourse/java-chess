package chess.view;

import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

public enum PieceMapper {

    KING('k'),
    QUEEN('q'),
    ROOK('r'),
    BISHOP('b'),
    KNIGHT('n'),
    PAWN('p'),
    EMPTY('.'),
    ;

    private final char name;

    PieceMapper(final char name) {
        this.name = name;
    }

    public static char map(final PieceType pieceType, final PieceColor pieceColor) {
        if (pieceColor == PieceColor.BLACK) {
            return Character.toUpperCase(valueOf(pieceType.name()).name);
        }
        return valueOf(pieceType.name()).name;
    }
}
