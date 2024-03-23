package chess.view;

import chess.domain.piece.PieceColor;

public enum PieceMapper {
    KING('k'),
    QUEEN('q'),
    ROOK('r'),
    BISHOP('b'),
    KNIGHT('n'),
    PAWN('p');

    private final char symbol;

    PieceMapper(char symbol) {
        this.symbol = symbol;
    }

    public static char map(String typeName, String colorName) {
        if (PieceColor.BLACK.isSame(colorName)) {
            return Character.toUpperCase(valueOf(typeName).symbol);
        }
        return valueOf(typeName).symbol;
    }
}
