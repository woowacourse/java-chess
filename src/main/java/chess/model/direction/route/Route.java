package chess.model.direction.route;

import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Objects;

public class Route {

    private final int y;
    private final int x;

    public Route(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Position createPositionFrom(final Rank rank, final File file) {
        return Position.of(rank.add(y), file.add(x));
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
