package chess.model.direction;

import chess.model.direction.route.Route;
import java.util.function.BiPredicate;

public enum DiagonalDirection implements Direction {

    NORTH_EAST((y, x) -> y > 0 && x < 0 && Math.abs(y) == Math.abs(x), new Route(-1, 1)),
    NORTH_WEST((y, x) -> y > 0 && x > 0 && Math.abs(y) == Math.abs(x), new Route(-1, -1)),
    SOUTH_EAST((y, x) -> y < 0 && x < 0 && Math.abs(y) == Math.abs(x), new Route(1, 1)),
    SOUTH_WEST((y, x) -> y < 0 && x > 0 && Math.abs(y) == Math.abs(x), new Route(1, -1)),
    ;

    private final BiPredicate<Integer, Integer> routeFinder;
    private final Route route;

    DiagonalDirection(final BiPredicate<Integer, Integer> routeFinder, final Route route) {
        this.routeFinder = routeFinder;
        this.route = route;
    }

    @Override
    public boolean findRouteFrom(final int rankDifference, final int fileDifference) {
        return routeFinder.test(rankDifference, fileDifference);
    }

    @Override
    public Route getRoute() {
        return route;
    }
}
