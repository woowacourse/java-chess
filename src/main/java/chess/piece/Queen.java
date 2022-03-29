package chess.piece;

import chess.Player;
import chess.Position;
import chess.direction.route.Route;
import chess.direction.strategy.RouteStrategy;
import chess.direction.strategy.RoyaltyRouteFinder;

public class Queen extends Piece {

    private static final double SCORE = 9;

    private final RouteStrategy routeStrategy;

    public Queen(Player player, String symbol) {
        super(player, symbol);
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
