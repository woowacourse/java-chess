package chess.model.direction.strategy;

import chess.model.direction.CardinalDirection;
import chess.model.direction.DiagonalDirection;
import chess.model.direction.Direction;
import chess.model.direction.route.Route;
import chess.model.position.Position;
import java.util.ArrayList;
import java.util.List;

public class OrdinalRouteFinder implements RouteStrategy {

    @Override
    public Route findRoute(final Position source, final Position target) {
        final int rankDifference = source.subtractRankFrom(target);
        final int fileDifference = source.subtractFileFrom(target);
        List<Direction> directions = createRoyaltyDirections();
        return directions.stream()
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(Direction::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }

    private List<Direction> createRoyaltyDirections() {
        final List<Direction> directions = new ArrayList<>();
        directions.addAll(List.of(CardinalDirection.values()));
        directions.addAll(List.of(DiagonalDirection.values()));
        return directions;
    }
}
