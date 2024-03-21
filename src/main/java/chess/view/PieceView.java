package chess.view;

import chess.domain.piece.Color;

public enum PieceView {
    KING('K'),
    QUEEN('Q'),
    PAWN('P'),
    ROOK('R'),
    BISHOP('B'),
    KNIGHT('N');

    private final char display;

    PieceView(final char display) {
        this.display = display;
    }

    public char changeToView(final String color) {
        if (Color.WHITE.isSame(color)) {
            return Character.toLowerCase(display);
        }
        return display;
    }
}
