package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.KnightRouteFinder;
import chess.model.direction.strategy.RouteStrategy;

public class Knight extends Piece {

    private static final double SCORE = 2.5;

    private final RouteStrategy routeStrategy;

    public Knight(Team team, String symbol) {
        super(team, symbol);
        this.routeStrategy = new KnightRouteFinder();
    }

    public Route findRoute(Position source, Position target) {
        return routeStrategy.findRoute(source, target);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
