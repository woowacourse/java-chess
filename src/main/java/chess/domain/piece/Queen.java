package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {

    private static final MovingStrategy MOVING_STRATEGY = new LinearMovingStrategy(List.of(Direction.values()));

    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        MOVING_STRATEGY.validateMove(board, sourcePosition, targetPosition);
    }
}
