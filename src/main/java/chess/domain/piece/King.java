package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;

public class King extends Piece {

    public King(Color color) {
        super(PieceType.KING, color, new LengthBasedMovingStrategy(number -> number > 2));
    }
}
