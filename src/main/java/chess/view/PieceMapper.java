package chess.view;

import chess.domain.PieceColor;
import chess.domain.PieceName;

public enum PieceMapper {

    KING('k'),
    QUEEN('q'),
    ROOK('r'),
    BISHOP('b'),
    KNIGHT('n'),
    PAWN('p');

    private final char name;

    PieceMapper(char name) {
        this.name = name;
    }

    public static char map(PieceName pieceName, PieceColor pieceColor) {
        if (pieceColor == PieceColor.BLACK) {
            return Character.toUpperCase(valueOf(pieceName.name()).name);
        }
        return valueOf(pieceName.name()).name;
    }
}
