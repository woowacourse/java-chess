package chess.direction.strategy;

import chess.Position;
import chess.direction.DiagonalDirection;
import chess.direction.route.Route;
import java.util.Arrays;

public class BishopRouteFinder implements RouteStrategy {
    @Override
    public Route findRoute(Position source, Position target) {
        final int rankDifference = source.subtractRankFrom(target);
        final int fileDifference = source.subtractFileFrom(target);
        return Arrays.stream(DiagonalDirection.values())
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(DiagonalDirection::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }
}
