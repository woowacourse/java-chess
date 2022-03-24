package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.UP;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.List;

public class WhitePawnMovableStrategy implements PieceMovableStrategy {

    private static final int MOVABLE_COUNT = 1;
    private static final Direction MOVE_DIRECTION = UP;

    @Override
    public boolean isMovable(Position start, Position target, ChessBoard chessBoard) {
        List<Position> route = MOVE_DIRECTION.route(start, target);
        return existMovablePosition(route) && isClearRoute(route, chessBoard);
    }

    private boolean existMovablePosition(List<Position> route) {
        return route.size() == MOVABLE_COUNT;
    }

    private boolean isClearRoute(List<Position> route, ChessBoard chessBoard) {
        return route.stream()
                .allMatch(chessBoard::isPositionEmpty);
    }
}
