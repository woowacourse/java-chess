package chess.model.piece;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.CardinalRouteFinder;
import chess.model.direction.strategy.RouteStrategy;
import chess.model.position.Position;

public class Rook extends Piece {

    private static final double SCORE = 5;

    private final RouteStrategy routeStrategy;

    public Rook(Team team) {
        super(team);
        this.routeStrategy = new CardinalRouteFinder();
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
