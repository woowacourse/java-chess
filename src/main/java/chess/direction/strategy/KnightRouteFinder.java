package chess.direction.strategy;

import chess.Position;
import chess.direction.KnightDirection;
import chess.direction.Route;

public class KnightRouteFinder implements RouteStrategy {

    @Override
    public Route findRoute(Position source, Position target) {
        final int subtractedRank = source.subtractRankFrom(target);
        final int subtractedFile = source.subtractFileFrom(target);
        return KnightDirection.findRouteFrom(subtractedRank, subtractedFile);
    }
}
