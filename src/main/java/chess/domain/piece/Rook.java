package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.List;

public class Rook extends Piece {

    private static final double ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, ROOK_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        List<Direction> movableDirections = Direction.rowAndColumns();
        return movableDirections.contains(direction);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
