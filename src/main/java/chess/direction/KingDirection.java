package chess.direction;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum KingDirection {

    EAST((y, x) -> y == 0 && x < 0, new Route(0, 1)),
    WEST((y, x) -> y == 0 && x > 0, new Route(0, -1)),
    SOUTH((y, x) -> y < 0 && x == 0, new Route(1, 0)),
    NORTH((y, x) -> y > 0 && x == 0, new Route(-1, 0)),
    NORTHEAST((y, x) -> y > 0 && x < 0 && Math.abs(y) == Math.abs(x), new Route(-1, 1)),
    NORTHWEST((y, x) -> y > 0 && x > 0 && Math.abs(y) == Math.abs(x), new Route(-1, -1)),
    SOUTHEAST((y, x) -> y < 0 && x < 0 && Math.abs(y) == Math.abs(x), new Route(1, 1)),
    SOUTHWEST((y, x) -> y < 0 && x > 0 && Math.abs(y) == Math.abs(x), new Route(1, -1)),
    ;

    private final BiPredicate<Integer, Integer> directionFinder;
    private final Route route;

    KingDirection(BiPredicate<Integer, Integer> directionFinder, Route route) {
        this.directionFinder = directionFinder;
        this.route = route;
    }

    public static Route findRouteFrom(final int y, final int x) {
        KingDirection direction = Arrays.stream(values())
                .filter(value -> value.directionFinder.test(y, x))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 기물이 해당 위치로 갈 수 없습니다."));
        return direction.route;
    }
}
