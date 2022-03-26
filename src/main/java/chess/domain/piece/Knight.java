package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;

public class Knight extends Piece {

    public Knight(Color color) {
        super(PieceType.KNIGHT, color, new LengthBasedMovingStrategy(number -> number != 5));
    }
}
