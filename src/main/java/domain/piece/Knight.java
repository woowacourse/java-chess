package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.Collections;

public final class Knight extends Piece {

    private static final int MIN_DISTANCE_OF_KNIGHT = 1;
    private static final int MAX_DISTANCE_OF_KNIGHT = 2;

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

        return (diffX == MIN_DISTANCE_OF_KNIGHT && diffY == MAX_DISTANCE_OF_KNIGHT) || (diffX == MAX_DISTANCE_OF_KNIGHT && diffY == MIN_DISTANCE_OF_KNIGHT);
    }

}
