package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.RouteStrategy;
import chess.model.direction.strategy.RoyaltyRouteFinder;

public class Queen extends Piece {

    private static final double SCORE = 9;

    private final RouteStrategy routeStrategy;

    public Queen(Team team, String symbol) {
        super(team, symbol);
        this.routeStrategy = new RoyaltyRouteFinder();
    }

    public Route findRoute(Position source, Position target) {
        return routeStrategy.findRoute(source, target);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
