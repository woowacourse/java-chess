package chess.piece;

import chess.Player;
import chess.Position;
import chess.direction.route.Route;
import chess.direction.strategy.BishopRouteFinder;
import chess.direction.strategy.RouteStrategy;

public class Bishop extends Piece {

    private static final double SCORE = 3;

    private final RouteStrategy routeStrategy;

    public Bishop(Player player, String symbol) {
        super(player, symbol);
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
