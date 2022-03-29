package chess.direction;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum RookDirection {

    EAST((y, x) -> y == 0 && x < 0, new Route(0, 1)),
    WEST((y, x) -> y == 0 && x > 0, new Route(0, -1)),
    SOUTH((y, x) -> y < 0 && x == 0, new Route(1, 0)),
    NORTH((y, x) -> y > 0 && x == 0, new Route(-1, 0)),
    ;

    private final BiPredicate<Integer, Integer> directionFinder;
    private final Route route;

    RookDirection(BiPredicate<Integer, Integer> directionFinder, Route route) {
        this.directionFinder = directionFinder;
        this.route = route;
    }

    public static Route findRouteFrom(final int y, final int x) {
        RookDirection direction = Arrays.stream(values())
                .filter(value -> value.directionFinder.test(y, x))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 기물이 이동할수 없는 Target이 입력 됬습니다."));
        return direction.route;
    }
}
