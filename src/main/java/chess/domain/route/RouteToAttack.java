package chess.domain.route;

import chess.domain.position.Position;

import java.util.List;

public class RouteToAttack extends Route {
    public RouteToAttack(List<Position> route) {
        super(route);
    }

    @Override
    public boolean isToAttack() {
        return true;
    }

    @Override
    public boolean isToProceed() {
        return false;
    }
}
