package chess.model.direction.strategy;

import chess.model.position.Position;
import chess.model.direction.KnightDirection;
import chess.model.direction.route.Route;
import java.util.Arrays;

public class KnightRouteFinder implements RouteStrategy {

    @Override
    public Route findRoute(Position source, Position target) {
        final int rankDifference = source.subtractRankFrom(target);
        final int fileDifference = source.subtractFileFrom(target);
        return Arrays.stream(KnightDirection.values())
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(KnightDirection::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }
}
