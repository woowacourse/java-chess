package chess.model.direction.strategy;

import chess.model.direction.DiagonalDirection;
import chess.model.direction.route.Route;
import chess.model.position.Position;
import java.util.Arrays;

public class DiagonalRouteFinder implements RouteStrategy {
    @Override
    public Route findRoute(final Position source, final Position target) {
        final int rankDifference = source.subtractRankFrom(target);
        final int fileDifference = source.subtractFileFrom(target);
        return Arrays.stream(DiagonalDirection.values())
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(DiagonalDirection::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }
}
