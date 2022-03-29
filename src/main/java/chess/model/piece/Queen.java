package chess.model.piece;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.OrdinalRouteFinder;
import chess.model.direction.strategy.RouteStrategy;
import chess.model.position.Position;

public class Queen extends Piece {

    private static final double SCORE = 9;

    private final RouteStrategy routeStrategy;

    public Queen(final Team team, final String symbol) {
        super(team, symbol);
        this.routeStrategy = new OrdinalRouteFinder();
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
