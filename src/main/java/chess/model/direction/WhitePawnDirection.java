package chess.model.direction;

import chess.model.direction.route.Route;
import java.util.function.BiPredicate;

public enum WhitePawnDirection implements Direction {

    NORTHEAST((y, x) -> y == 1 && x == -1, new Route(-1, 1)),
    NORTHWEST((y, x) -> y == 1 && x == 1, new Route(-1, -1)),
    NORTH((y, x) -> y == 1 && x == 0, new Route(-1, 0)),
    NORTHTWICE((y, x) -> y == 2 && x == 0, new Route(-2, 0)),
    ;

    private final BiPredicate<Integer, Integer> routeFinder;
    private final Route route;

    WhitePawnDirection(final BiPredicate<Integer, Integer> routeFinder, final Route route) {
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
