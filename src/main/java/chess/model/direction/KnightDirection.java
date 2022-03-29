package chess.model.direction;

import chess.model.direction.route.Route;
import java.util.function.BiPredicate;

public enum KnightDirection implements Direction {

    EEN((y, x) -> y == 1 && x == -2, new Route(-1, 2)),
    EES((y, x) -> y == -1 && x == -2, new Route(1, 2)),
    WWN((y, x) -> y == 1 && x == 2, new Route(-1, -2)),
    WWS((y, x) -> y == -1 && x == 2, new Route(1, -2)),
    SSE((y, x) -> y == -2 && x == -1, new Route(2, 1)),
    SSW((y, x) -> y == -2 && x == 1, new Route(2, -1)),
    NNE((y, x) -> y == 2 && x == -1, new Route(-2, 1)),
    NNW((y, x) -> y == 2 && x == 1, new Route(-2, -1)),
    ;

    private final BiPredicate<Integer, Integer> routeFinder;
    private final Route route;

    KnightDirection(BiPredicate<Integer, Integer> routeFinder, Route route) {
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
