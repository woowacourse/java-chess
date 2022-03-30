package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final int SCOPE_DIFFERENCE = 1;
    private static final double KING_SCORE = 0;

    public King(Color color) {
        super(color, KING_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        Direction direction = Direction.giveDirection(fromPosition, toPosition);
        List<Direction> movableDirections = new ArrayList<>(Direction.rowAndColumns());
        movableDirections.addAll(Direction.diagonals());
        return movableDirections.contains(direction) && isOneStep(fromPosition, toPosition);
    }

    private boolean isOneStep(Position fromPosition, Position toPosition) {
        return toPosition.getRankDifference(fromPosition) <= SCOPE_DIFFERENCE
            && toPosition.getFileDifference(fromPosition) <= SCOPE_DIFFERENCE;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
