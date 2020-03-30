package chess.domain.route;

import chess.domain.position.Position;

import java.util.List;

public class RouteToProceed extends Route {
    public RouteToProceed(List<Position> route) {
        super(route);
    }

    @Override
    public boolean isToAttack() {
        return false;
    }

    @Override
    public boolean isToProceed() {
        return true;
    }
}
