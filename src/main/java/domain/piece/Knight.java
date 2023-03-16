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

    private void validateMovable(final Position source, final Position target) {
        if (isMovable(source, target)) {
            return;
        }
        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    private boolean isMovable(final Position startPoint, final Position endPoint) {
        int diffY = Math.abs(endPoint.diffY(startPoint));
        int diffX = Math.abs(endPoint.diffX(startPoint));

        Set<Integer> set = new HashSet<>();
        set.add(diffX);
        set.add(diffY);

        return set.contains(1) && set.contains(2);
    }

}
