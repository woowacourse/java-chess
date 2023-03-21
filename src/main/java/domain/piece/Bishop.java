package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.squarestatus.Piece;
import domain.type.PieceType;

public final class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        int moveX = getMoveCoordinate(target.diffX(source));
        int moveY = getMoveCoordinate(target.diffY(source));

        return new Route(findPositions(source, target, moveX, moveY));
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return source.isDiagonally(target);
    }

    private int getMoveCoordinate(final int diff) {
        if (diff < 0) {
            return -1;
        }

        return 1;
    }

}
