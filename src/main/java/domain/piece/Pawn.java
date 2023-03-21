package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.Collections;

public class Pawn extends PawnFeature {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(MovePosition movePosition) {
        validateMovable(movePosition);

        return new Route(Collections.emptyList());
    }

    @Override
    protected boolean isMovable(MovePosition movePosition) {
        int direction = chooseDirection();

        int diffY = movePosition.diffY();
        int diffX = movePosition.diffX();

        return isPawnMovable(direction, diffY, diffX);
    }

}
