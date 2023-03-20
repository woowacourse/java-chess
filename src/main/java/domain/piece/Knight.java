package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.Collections;

public final class Knight extends Piece {

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
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

        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
    }

}
