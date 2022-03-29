package chess.model.direction.strategy;

import chess.model.direction.DiagonalDirection;
import chess.model.direction.route.Route;
import chess.model.position.Position;
import chess.model.direction.CardinalDirection;
import chess.model.direction.Direction;
import java.util.ArrayList;
import java.util.List;

public class PawnRouteFinder implements RouteStrategy {

    @Override
    public Route findRoute(final Position source, final Position target) {
        final int rankDifference = source.subtractRankFrom(target);
        final int fileDifference = source.subtractFileFrom(target);
        List<Direction> directions = createPawnDirections();
        return directions.stream()
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(Direction::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 현재 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }

    private List<Direction> createPawnDirections() {
        List<Direction> directions = new ArrayList<>();
        directions.addAll(List.of(CardinalDirection.values()));
        directions.addAll(List.of(DiagonalDirection.values()));
        return directions;
    }
}
