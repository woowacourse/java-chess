package chess.direction.strategy;

import chess.Position;
import chess.direction.CardinalDirection;
import chess.direction.DiagonalDirection;
import chess.direction.Direction;
import chess.direction.route.Route;
import java.util.ArrayList;
import java.util.List;

public class RoyaltyRouteFinder implements RouteStrategy {

    @Override
    public Route findRoute(final Position source, final Position target) {
        final int rankDifference = source.subtractRankFrom(target);
        final int fileDifference = source.subtractFileFrom(target);
        List<Direction> directions = new ArrayList<>();
        directions.addAll(List.of(CardinalDirection.values()));
        directions.addAll(List.of(DiagonalDirection.values()));
        return directions.stream()
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(Direction::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }
}
