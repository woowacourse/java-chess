package chess.domain.piece;

import chess.domain.Color;

public class Queen extends Piece {

    private static final String NOTATION = "Q";

    public Queen(Color color) {
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
