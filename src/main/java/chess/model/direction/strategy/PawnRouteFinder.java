package chess.model.direction.strategy;

import static chess.model.Team.WHITE;

import chess.model.Team;
import chess.model.direction.BlackPawnDirection;
import chess.model.direction.WhitePawnDirection;
import chess.model.direction.route.Route;
import chess.model.position.Position;
import java.util.Arrays;

public class PawnRouteFinder implements RouteStrategy {

    private final Team team;

    public PawnRouteFinder(Team team) {
        this.team = team;
    }

    @Override
    public Route findRoute(Position source, Position target) {
        final int rankDifference = source.subtractRankFrom(target);
        final int fileDifference = source.subtractFileFrom(target);
        if (team == WHITE) {
            return findRouteWhitePawn(rankDifference, fileDifference);
        }
        return findRouteBlackPawn(rankDifference, fileDifference);
    }

    private Route findRouteWhitePawn(int rankDifference, int fileDifference) {
        return Arrays.stream(WhitePawnDirection.values())
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(WhitePawnDirection::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }

    private Route findRouteBlackPawn(int rankDifference, int fileDifference) {
        return Arrays.stream(BlackPawnDirection.values())
                .filter(direction -> direction.findRouteFrom(rankDifference, fileDifference))
                .map(BlackPawnDirection::getRoute)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택한 기물을 이동 할 수 없는 위치가 입력됬습니다."));
    }
}
