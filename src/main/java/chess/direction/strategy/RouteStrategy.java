package chess.direction.strategy;

import chess.Position;
import chess.direction.Route;

public interface RouteStrategy {

    Route findRoute(final Position source, final Position target);
}
