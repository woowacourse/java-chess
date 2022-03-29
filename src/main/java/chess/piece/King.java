package chess.piece;

import chess.Player;
import chess.Position;
import chess.direction.route.Route;
import chess.direction.strategy.RouteStrategy;
import chess.direction.strategy.RoyaltyRouteFinder;

public class King extends Piece {

    private static final int MOVABLE_DISTANCE = 1;

    private static final double SCORE = 0;

    private final RouteStrategy routeStrategy;

    public King(Player player, String symbol) {
        super(player, symbol);
        this.routeStrategy = new RoyaltyRouteFinder();
    }

    public Route findRoute(Position source, Position target) {
        checkMovableDistance(source, target);
        return routeStrategy.findRoute(source, target);
    }

    private void checkMovableDistance(Position source, Position target) {
        int rankDifference = Math.abs(source.subtractRankFrom(target));
        int fileDifference = Math.abs(source.subtractFileFrom(target));
        if (rankDifference > MOVABLE_DISTANCE || fileDifference > MOVABLE_DISTANCE) {
            throw new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
