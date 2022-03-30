package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final double QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, QUEEN_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        List<Direction> movableDirections = new ArrayList<>(Direction.rowAndColumns());
        movableDirections.addAll(Direction.diagonals());
        return movableDirections.contains(direction);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
