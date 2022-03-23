package chess.domain.piece;

import chess.domain.Color;

public class Bishop extends Piece {

    private static final String NOTATION = "B";

    public Bishop(Color color) {
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
