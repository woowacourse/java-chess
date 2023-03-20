package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.Collections;

public final class King extends Piece {

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public Route findRoute(MovePosition movePosition) {
        validateMovable(movePosition);
        return new Route(Collections.emptyList());
    }

    @Override
    protected boolean isMovable(MovePosition movePosition) {
        int diffY = Math.abs(movePosition.diffY());
        int diffX = Math.abs(movePosition.diffX());

        return (diffY != 0 || diffX != 0) && (diffX < 2 && diffY < 2);
    }

}
