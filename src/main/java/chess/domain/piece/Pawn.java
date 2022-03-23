package chess.domain.piece;

import chess.domain.Color;

public class Pawn extends Piece {

    private static final String NOTATION = "P";

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}
