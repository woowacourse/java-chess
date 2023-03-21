package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Route;

public final class Rook extends Piece implements VariableMover {

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public Route findRoute(MovePosition movePosition) {
        validateMovable(movePosition);

        int moveX = getMoveCoordinate(movePosition.diffX());
        int moveY = getMoveCoordinate(movePosition.diffY());

        return new Route(findPositions(movePosition, moveX, moveY));
    }

    @Override
    protected boolean isMovable(MovePosition movePosition) {
        return movePosition.isStraight();
    }

    public int getMoveCoordinate(final int diff) {
        return Integer.compare(diff, 0);
    }

}
