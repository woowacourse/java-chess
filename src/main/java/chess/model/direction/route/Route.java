package chess.model.direction.route;

import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Objects;

public class Route {

    private static final Route NORTH = new Route(-1, 0);
    private static final Route NORTHEAST = new Route(-1, 1);
    private static final Route NORTHWEST = new Route(-1, -1);
    private static final Route SOUTH = new Route(1, 0);
    private static final Route SOUTHEAST = new Route(1, 1);
    private static final Route SOUTHWEST = new Route(1, -1);

    private final int y;
    private final int x;

    public Route(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Position createPositionFrom(final Rank rank, final File file) {
        return Position.of(rank.add(y), file.add(x));
    }

    public void checkWhitePawnRoute() {
        if (!this.equals(NORTH) && !this.equals(NORTHEAST) && !this.equals(NORTHWEST)) {
            throw new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    public void checkBlackPawnRoute() {
        if (!this.equals(SOUTH) && !this.equals(SOUTHEAST) && !this.equals(SOUTHWEST)) {
            throw new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return y == route.y && x == route.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}
