package chess.model.piece;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.PawnRouteFinder;
import chess.model.direction.strategy.RouteStrategy;
import chess.model.position.Position;
import java.util.Map;

public class BlackPawn extends Piece {

    private static final Route SOUTH = new Route(1, 0);
    private static final Route SOUTH_TWICE = new Route(2, 0);
    private static final Route SOUTH_EAST = new Route(1, 1);
    private static final Route SOUTH_WEST = new Route(1, -1);
    private static final double SCORE = 1;

    private final RouteStrategy routeStrategy;

    public BlackPawn(Team team) {
        super(team);
        this.routeStrategy = new PawnRouteFinder(team);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        return routeStrategy.findRoute(source, target);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        Route route = routeStrategy.findRoute(source, target);
        if (route.equals(SOUTH_WEST) || route.equals(SOUTH_EAST)) {
            checkCanKillOpponent(target, board);
        }
        if (route.equals(SOUTH)) {
            checkCanBaseMove(target, board);
        }
        if (route.equals(SOUTH_TWICE)) {
            checkCanSpecialMove(source, target, board);
        }
        return true;
    }

    private void checkCanKillOpponent(Position target, Map<Position, Piece> board) {
        if (!board.get(target).isOpponent(this.team)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void checkCanBaseMove(Position target, Map<Position, Piece> board) {
        if (!board.get(target).isSame(NONE)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void checkCanSpecialMove(Position source, Position target, Map<Position, Piece> board) {
        Position nextPosition = source.createPositionTo(SOUTH);
        while (!target.equals(nextPosition) && board.get(nextPosition).isSame(NONE)) {
            nextPosition = nextPosition.createPositionTo(SOUTH);
        }
        if (!source.isSevenRank() || !target.equals(nextPosition)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
