package chess.domain.piece;

import chess.domain.Color;

public class Knight extends Piece {

    private static final String NOTATION = "N";

    public Knight(Color color) {
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
