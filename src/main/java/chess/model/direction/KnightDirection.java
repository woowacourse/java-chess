package chess.model.direction;

import chess.model.direction.route.Route;
import java.util.function.BiPredicate;

public enum KnightDirection implements Direction {

    EAST_TWICE_NORTH((y, x) -> y == 1 && x == -2, new Route(-1, 2)),
    EAST_TWICE_SOUTH((y, x) -> y == -1 && x == -2, new Route(1, 2)),
    WEST_TWICE_NORTH((y, x) -> y == 1 && x == 2, new Route(-1, -2)),
    WEST_TWICE_SOUTH((y, x) -> y == -1 && x == 2, new Route(1, -2)),
    SOUTH_TWICE_EAST((y, x) -> y == -2 && x == -1, new Route(2, 1)),
    SOUTH_TWICE_WEST((y, x) -> y == -2 && x == 1, new Route(2, -1)),
    NORTH_TWICE_EAST((y, x) -> y == 2 && x == -1, new Route(-2, 1)),
    NORTH_TWICE_WESR((y, x) -> y == 2 && x == 1, new Route(-2, -1)),
    ;

    private final BiPredicate<Integer, Integer> routeFinder;
    private final Route route;

    KnightDirection(final BiPredicate<Integer, Integer> routeFinder, final Route route) {
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
