package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;

public class Knight extends Piece {

    private static final int MOVABLE_LENGTH = 5;

    public Knight(Color color) {
        super(PieceType.KNIGHT, color, new LengthBasedMovingStrategy(number -> number != MOVABLE_LENGTH));
    }
}
