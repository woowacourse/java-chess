package chess.domain.routeFinder;

import chess.domain.Direction;
import chess.domain.Route;
import chess.domain.Team;
import chess.domain.position.Position;

public class BishopRouteFinder implements RouteFinder {
    @Override
    public Route findRoute(Position fromPosition, Position toPosition, Team team) {
        // 기물이 이동하는 방향별로 포지션을 구해서 거기에 toPosition이 속하는지 확인
        Route movableRoute = null;
        boolean isMovable = false;

        // 이동할 수 있는 방향별로,
        for (Direction direction : Direction.diagonalDirection()) {
            // 각 방향별로 가능한 경로를 찾는다.
            movableRoute = Route.findEveryRouteToOneDirection(fromPosition, toPosition, direction);

            // 경로에 목적지가 존재하면
            if (movableRoute.contains(toPosition)) {
                isMovable = true;
                break;
            }
        }

        if (!isMovable) {
            throw new IllegalArgumentException("선택한 기물이 움직일 수 없는 위치입니다.");
        }

        return movableRoute;
    }
}
