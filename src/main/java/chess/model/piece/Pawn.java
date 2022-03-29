package chess.model.piece;

import static chess.model.Team.BLACK;
import static chess.model.Team.WHITE;

import chess.model.Team;
import chess.model.position.Position;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.RouteStrategy;
import chess.model.direction.strategy.OrdinalRouteFinder;

public class Pawn extends Piece {

    private static final int MAX_MOVABLE_DISTANCE = 2;
    private static final int BASE_MOVABLE_DISTANCE = 1;
    private static final double SCORE = 1;

    private final RouteStrategy routeStrategy;

    public Pawn(Team team, String symbol) {
        super(team, symbol);
        this.routeStrategy = new OrdinalRouteFinder();
    }

    public Route findRoute(Position source, Position target) {
        Route route = routeStrategy.findRoute(source, target);
        checkMovableRoute(route);
        checkFirstMovePosition(source, target);
        checkMovableDistance(source, target);
        return route;
    }

    private void checkMovableRoute(Route route) {
        if (this.team == WHITE) {
            route.checkWhitePawnRoute();
        }
        if (this.team == BLACK) {
            route.checkBlackPawnRoute();
        }
    }

    private void checkFirstMovePosition(Position source, Position target) {
        int rankDifference = Math.abs(source.subtractRankFrom(target));
        if (rankDifference == MAX_MOVABLE_DISTANCE && source.isNotInitialPawnPosition()) {
            throw new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void checkMovableDistance(Position source, Position target) {
        int rankDifference = Math.abs(source.subtractRankFrom(target));
        int fileDifference = Math.abs(source.subtractFileFrom(target));
        if (rankDifference > MAX_MOVABLE_DISTANCE) {
            throw new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
        if (fileDifference > BASE_MOVABLE_DISTANCE) {
            throw new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
