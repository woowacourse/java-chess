package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;

public class King extends Piece {

    private static final MovingStrategy MOVING_STRATEGY =
            new LengthBasedMovingStrategy(number -> number > 2);

    public King(Color color) {
        super(PieceType.KING, color);
    }

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        MOVING_STRATEGY.validateMove(board, sourcePosition, targetPosition);
    }
}
