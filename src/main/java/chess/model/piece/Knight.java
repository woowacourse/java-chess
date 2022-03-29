package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.KnightRouteFinder;
import chess.model.direction.strategy.RouteStrategy;

public class Knight extends Piece {

    private static final double SCORE = 2.5;

    private final RouteStrategy routeStrategy;

    public Knight(final Team team, final String symbol) {
        super(team, symbol);
        this.routeStrategy = new KnightRouteFinder();
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        return routeStrategy.findRoute(source, target);
    }

    @Override
    public double addTo(final double score) {
        return score + SCORE;
    }
}
