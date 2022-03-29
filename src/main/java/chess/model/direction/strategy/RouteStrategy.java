package chess.model.direction.strategy;

import chess.model.direction.route.Route;
import chess.model.position.Position;

public interface RouteStrategy {

    Route findRoute(final Position source, final Position target);
}
