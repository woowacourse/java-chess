package domain.position;

import java.util.List;

public final class Route {

    private final List<Position> positions;

    public Route(final List<Position> positions) {
        this.positions = positions;
    }

    public List<Position> getRoute() {
        return positions;
    }

}
