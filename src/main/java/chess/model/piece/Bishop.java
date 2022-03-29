package chess.model.piece;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.DiagonalRouteFinder;
import chess.model.direction.strategy.RouteStrategy;
import chess.model.position.Position;

public class Bishop extends Piece {

    private static final double SCORE = 3;

    private final RouteStrategy routeStrategy;

    public Bishop(final Team team, final String symbol) {
        super(team, symbol);
        this.routeStrategy = new DiagonalRouteFinder();
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
