package chess.piece;

import chess.Player;
import chess.Position;
import chess.direction.route.Route;
import chess.direction.strategy.KnightRouteFinder;
import chess.direction.strategy.RouteStrategy;

public class Knight extends Piece {

    private static final double SCORE = 2.5;

    private final RouteStrategy routeStrategy;

    public Knight(Player player, String symbol) {
        super(player, symbol);
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
