package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.type.PieceType;

public final class Rook extends VariableMover {

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        final Position direction = findDirection(source, target);

        return new Route(findPositions(source, target, direction));
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return source.isStraight(target);
    }

    public int getMoveCoordinate(final int diff) {
        return Integer.compare(diff, 0);
    }

}
