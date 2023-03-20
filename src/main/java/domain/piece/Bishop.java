package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.List;

public final class Bishop extends Piece implements VariableMover {

    private static final int UNIT_MOVE_DISTANCE = 1;

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public Route findRoute(MovePosition movePosition) {
        validateMovable(movePosition);

        return new Route(getPositions(movePosition));
    }

    @Override
    protected boolean isMovable(MovePosition movePosition) {
        return movePosition.isDiagonally();
    }

    private List<Position> getPositions(MovePosition movePosition) {
        int moveX = getMoveCoordinate(movePosition.diffX());
        int moveY = getMoveCoordinate(movePosition.diffY());

        return findPositions(movePosition, moveX, moveY);
    }

    private int getMoveCoordinate(final int diff) {
        if (diff < 0) {
            return -UNIT_MOVE_DISTANCE;
        }

        return UNIT_MOVE_DISTANCE;
    }

}
