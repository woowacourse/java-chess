package chess.domain.piece.multiple;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.PieceRule;
import java.util.Collections;
import java.util.List;

public abstract class MultipleMovePiece implements PieceRule {

    private final List<Direction> moveDirections;

    protected MultipleMovePiece(List<Direction> moveDirections) {
        this.moveDirections = moveDirections;
    }

    @Override
    public final PieceRule move(Position source, Position target, ChessBoard chessBoard) {
        if (isMovable(source, target, chessBoard)) {
            return this;
        }
        throw new IllegalStateException("움직일 수 없는 곳입니다.");
    }

    private boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        List<Position> route = calculateRoute(source, target);
        if (route.isEmpty()) {
            return false;
        }
        return isEmptyRouteWithoutTargetPosition(target, chessBoard, route);
    }

    private List<Position> calculateRoute(Position source, Position target) {
        return moveDirections.stream()
                .map(direction -> direction.route(source, target))
                .filter(route -> !route.isEmpty())
                .findAny()
                .orElse(Collections.emptyList());
    }

    private boolean isEmptyRouteWithoutTargetPosition(Position target, ChessBoard chessBoard, List<Position> route) {
        return route.stream()
                .filter(position -> !position.equals(target))
                .allMatch(chessBoard::isPositionEmpty);
    }
}
