package chess.direction.strategy;

import chess.Position;
import chess.direction.KingDirection;
import chess.direction.Route;

public class KingRouteFinder implements RouteStrategy {

    @Override
    public Route findRoute(final Position source, final Position target) {
        final int subtractedRank = source.subtractRankFrom(target);
        final int subtractedFile = source.subtractFileFrom(target);
        return KingDirection.findRouteFrom(subtractedRank, subtractedFile);
    }
}
