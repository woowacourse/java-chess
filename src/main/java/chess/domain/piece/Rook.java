package chess.domain.piece;

import static chess.domain.position.Direction.BOTTOM;
import static chess.domain.position.Direction.LEFT;
import static chess.domain.position.Direction.RIGHT;
import static chess.domain.position.Direction.TOP;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {

    private static final MovingStrategy MOVING_STRATEGY = new LinearMovingStrategy(List.of(RIGHT, LEFT, TOP, BOTTOM));

    public Rook(Color color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        MOVING_STRATEGY.validateMove(board, sourcePosition, targetPosition);
    }
}
