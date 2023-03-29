package domain.piece;

import domain.piece.abstractpiece.PawnFeature;
import domain.position.Position;
import domain.position.Route;

import java.util.Collections;
import java.util.List;

public final class InitPawn extends PawnFeature {

    private static final int STAY = 0;
    private static final int FIRST_MOVE = 2;

    public InitPawn(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        return new Route(findPositions(source, target));
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int direction = chooseDirection();

        int diffY = target.diffY(source);
        int diffX = target.diffX(source);

        return isPawnMovable(direction, diffY, diffX) || diffY == direction * FIRST_MOVE && diffX == STAY;
    }

    private List<Position> findPositions(final Position source, final Position target) {
        if (target.diffY(source) == chooseDirection()) {
            return Collections.emptyList();
        }

        return List.of(source.move(Position.of(STAY, chooseDirection())));
    }

}
