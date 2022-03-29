package chess.direction;

import chess.direction.route.Route;
import java.util.function.BiPredicate;

public enum DiagonalDirection implements Direction {

    NORTHEAST((y, x) -> y > 0 && x < 0 && Math.abs(y) == Math.abs(x), new Route(-1, 1)),
    NORTHWEST((y, x) -> y > 0 && x > 0 && Math.abs(y) == Math.abs(x), new Route(-1, -1)),
    SOUTHEAST((y, x) -> y < 0 && x < 0 && Math.abs(y) == Math.abs(x), new Route(1, 1)),
    SOUTHWEST((y, x) -> y < 0 && x > 0 && Math.abs(y) == Math.abs(x), new Route(1, -1)),
    ;

    private final BiPredicate<Integer, Integer> routeFinder;
    private final Route route;

    DiagonalDirection(BiPredicate<Integer, Integer> routeFinder, Route route) {
        this.routeFinder = routeFinder;
        this.route = route;
    }

    @Override
    public boolean findRouteFrom(int rankDifference, int fileDifference) {
        return routeFinder.test(rankDifference, fileDifference);
    }

    @Override
    public Route getRoute() {
        return route;
    }
}
