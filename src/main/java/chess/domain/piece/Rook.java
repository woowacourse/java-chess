package chess.domain.piece;

import chess.domain.Color;

public class Rook extends Piece {

    private static final String NOTATION = "R";

    public Rook(Color color) {
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