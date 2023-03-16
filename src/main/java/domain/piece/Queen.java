package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        return null;
    }
}
