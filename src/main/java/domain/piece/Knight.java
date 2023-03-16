package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);
        return new Route(Collections.emptyList());
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int diffY = Math.abs(target.diffY(source));
        int diffX = Math.abs(target.diffX(source));

        Set<Integer> set = new HashSet<>();
        set.add(diffX);
        set.add(diffY);

        return set.contains(1) && set.contains(2);
    }

}
