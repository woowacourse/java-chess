package chess.model.piece;

import static chess.model.Team.NONE;

import chess.model.Team;
import chess.model.direction.route.Route;
import chess.model.direction.strategy.PawnRouteFinder;
import chess.model.direction.strategy.RouteStrategy;
import chess.model.position.Position;
import java.util.Map;

public class WhitePawn extends Piece {

    private static final Route NORTH = new Route(-1, 0);
    private static final Route NORTHTWICE = new Route(-2, 0);
    private static final Route NORTHEAST = new Route(-1, 1);
    private static final Route NORTHWEST = new Route(-1, -1);
    private static final double SCORE = 1;

    private final RouteStrategy routeStrategy;

    public WhitePawn(final Team team, final String symbol) {
        super(team, symbol);
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
        if (route.equals(NORTHWEST) || route.equals(NORTHEAST)) {
            checkCanKillOpponent(target, board);
        }
        if (route.equals(NORTH)) {
            checkCanBaseMove(target, board);
        }
        if (route.equals(NORTHTWICE)) {
            checkCanSpecialMove(source, target, board);
        }
        return true;
    }

    private void checkCanKillOpponent(Position target, Map<Position, Piece> board) {
        if (board.get(target).isSame(NONE) || board.get(target).isSame(this.team)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void checkCanBaseMove(Position target, Map<Position, Piece> board) {
        if (!board.get(target).isSame(NONE)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    private void checkCanSpecialMove(Position source, Position target, Map<Position, Piece> board) {
        Position nextPosition = source.createPositionTo(NORTH);
        while (!target.equals(nextPosition) && board.get(nextPosition).isSame(NONE)) {
            nextPosition = nextPosition.createPositionTo(NORTH);
        }
        if (!source.isTwoRank() || !target.equals(nextPosition)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다.");
        }
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
