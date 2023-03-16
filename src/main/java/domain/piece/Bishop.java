package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        return null;
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return false;
    }
}
