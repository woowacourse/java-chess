package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.List;

public class Bishop extends Piece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color, BISHOP_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        List<Direction> movableDirections = Direction.diagonals();
        return movableDirections.contains(direction);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
