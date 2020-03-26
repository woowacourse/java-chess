package chess.domain.move;

import java.util.List;

public class Route {
    private final List<Position> route;

    public Route(List<Position> route) {
        this.route = route;
    }

    public List<Position> getRoute() {
        return route;
    }

    public boolean hasPosition(Position targetPosition) {
        return route.contains(targetPosition);
    }
}
