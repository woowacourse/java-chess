package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(final Position startPoint, final Position endPoint) {
        return null;
    }
}
