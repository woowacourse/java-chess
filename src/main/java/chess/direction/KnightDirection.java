package chess.direction;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum KnightDirection {

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

    public static Route findRouteFrom(final int y, final int x) {
        KnightDirection direction = Arrays.stream(values())
                .filter(value -> value.routeFinder.test(y, x))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 기물이 이동할수 없는 Target이 입력 됬습니다."));
        return direction.route;
    }
}
