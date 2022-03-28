package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final MovingStrategy MOVING_STRATEGY =
            new LengthBasedMovingStrategy(number -> number != 5);

    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        MOVING_STRATEGY.validateMove(board, sourcePosition, targetPosition);
    }
}
