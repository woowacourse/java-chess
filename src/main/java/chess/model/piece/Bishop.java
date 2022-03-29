package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.BishopRouteFinder;
import chess.model.direction.strategy.RouteStrategy;

public class Bishop extends Piece {

    private static final double SCORE = 3;

    private final RouteStrategy routeStrategy;

    public Bishop(Team team, String symbol) {
        super(team, symbol);
        this.routeStrategy = new BishopRouteFinder();
    }

    public Route findRoute(Position source, Position target) {
        return routeStrategy.findRoute(source, target);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
