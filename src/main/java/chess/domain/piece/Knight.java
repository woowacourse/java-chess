package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;

public class Knight extends Piece {

    private static final String NOTATION = "N";

    public Knight(Color color) {
        super(color, new LengthBasedMovingStrategy(number -> number != 5));
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}
