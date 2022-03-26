package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.BlackPawnMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.WhitePawnMovingStrategy;

public class Pawn extends Piece {

    private static final String NOTATION = "P";

    public Pawn(Color color) {
        super(color, getMovingStrategy(color));
    }

    private static MovingStrategy getMovingStrategy(Color color) {
        if (color.isBlack()) {
            return new BlackPawnMovingStrategy();
        }

        return new WhitePawnMovingStrategy();
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}
