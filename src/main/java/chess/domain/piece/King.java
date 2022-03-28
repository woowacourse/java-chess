package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;

public class King extends Piece {

    private static final int MOVABLE_LENGTH = 2;

    public King(Color color) {
        super(PieceType.KING, color, new LengthBasedMovingStrategy(number -> number > MOVABLE_LENGTH));
    }
}
