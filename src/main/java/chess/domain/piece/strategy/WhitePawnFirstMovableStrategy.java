package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public class WhitePawnFirstMovableStrategy implements PieceMovableStrategy {

    private static final int MOVABLE_COUNT = 2;
    private static final Direction MOVE_DIRECTION = UP;
    private static final List<Direction> MOVE_DIRECTION_TO_ENEMY = Arrays.asList(UP_RIGHT, UP_LEFT);

    @Override
    public boolean isMovable(Position start, Position target, ChessBoard chessBoard) {
        List<Position> route = MOVE_DIRECTION.route(start, target);
        return existMovablePosition(route) && isClearRoute(route, chessBoard);
    }

    private boolean existMovablePosition(List<Position> route) {
        return !route.isEmpty() && route.size() <= MOVABLE_COUNT;
    }

    private boolean isClearRoute(List<Position> route, ChessBoard chessBoard) {
        return route.stream()
                .allMatch(chessBoard::isPositionEmpty);
    }
}
