package chess.domain.route;

import chess.domain.Team;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class RouteFinder {
    public static Route findRoute(Position fromPosition, Position toPosition, Team team, PieceType pieceType) {
        return Directions.of(pieceType, team).stream()
                .map(direction -> Route.findRoute(fromPosition, direction, pieceType))
                .filter(route -> route.contains(toPosition))
                .map(route -> route.cutBefore(toPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("선택한 기물이 움직일 수 없는 위치입니다."));
    }
}