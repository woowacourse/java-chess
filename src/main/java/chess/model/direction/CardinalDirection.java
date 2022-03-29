package chess.model.direction;

import chess.model.direction.route.Route;
import java.util.function.BiPredicate;

public enum CardinalDirection implements Direction {

    EAST((y, x) -> y == 0 && x < 0, new Route(0, 1)),
    WEST((y, x) -> y == 0 && x > 0, new Route(0, -1)),
    SOUTH((y, x) -> y < 0 && x == 0, new Route(1, 0)),
    NORTH((y, x) -> y > 0 && x == 0, new Route(-1, 0)),
    ;

    private final BiPredicate<Integer, Integer> routeFinder;
    private final Route route;

    CardinalDirection(BiPredicate<Integer, Integer> routeFinder, Route route) {
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
