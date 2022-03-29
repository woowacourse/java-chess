package chess.piece;

import chess.Player;
import chess.Position;
import chess.direction.route.Route;
import chess.direction.strategy.RookRouteFinder;
import chess.direction.strategy.RouteStrategy;

public class Rook extends Piece {

    private static final double SCORE = 5;

    private final RouteStrategy routeStrategy;

    public Rook(Player player, String symbol) {
        super(player, symbol);
        this.routeStrategy = new RookRouteFinder();
    }

    public Route findRoute(Position source, Position target) {
        return routeStrategy.findRoute(source, target);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
