package domain.coordinate;

import java.util.List;

public class Route {

    private final List<Position> route;

    public Route(final List<Position> route) {
        this.route = route;
    }

    public List<Position> getRoute() {
        return route;
    }

}
