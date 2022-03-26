package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;

public class King extends Piece {

    private static final String NOTATION = "K";

    public King(Color color) {
        super(color, new LengthBasedMovingStrategy(number -> number > 2));
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}
