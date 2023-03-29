package domain.piece;

import domain.piece.abstractpiece.PawnFeature;
import domain.position.Position;
import domain.position.Route;

import java.util.Collections;

public final class Pawn extends PawnFeature {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        return new Route(Collections.emptyList());
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int direction = chooseDirection();

        int diffY = target.diffY(source);
        int diffX = target.diffX(source);

        return isPawnMovable(direction, diffY, diffX);
    }

}
