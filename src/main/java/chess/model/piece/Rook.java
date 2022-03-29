package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.CardinalRouteFinder;
import chess.model.direction.strategy.RouteStrategy;

public class Rook extends Piece {

    private static final double SCORE = 5;

    private final RouteStrategy routeStrategy;

    public Rook(Team team, String symbol) {
        super(team, symbol);
        this.routeStrategy = new CardinalRouteFinder();
    }

    public Route findRoute(Position source, Position target) {
        return routeStrategy.findRoute(source, target);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
