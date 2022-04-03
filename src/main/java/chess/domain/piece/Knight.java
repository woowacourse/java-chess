package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.List;

public class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;
    public static final int MAXIMUM_DIFFERENCE = 2;

    public Knight(Color color) {
        super(color, KNIGHT_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        List<Direction> directions = Direction.knightDirections();
        return directions.contains(direction)
                && Math.abs(toPosition.getRankDifference(fromPosition)) <= MAXIMUM_DIFFERENCE
                && Math.abs(toPosition.getFileDifference(fromPosition)) <= MAXIMUM_DIFFERENCE;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
