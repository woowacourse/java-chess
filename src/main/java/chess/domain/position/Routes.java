package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

public class Routes {
    private final List<Position> routes;

    public Routes(final List<Position> routes) {
        this.routes = new ArrayList<>(routes);
    }
}
