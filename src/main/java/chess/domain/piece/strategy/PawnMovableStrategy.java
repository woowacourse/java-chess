package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public abstract class PawnMovableStrategy implements PieceMovableStrategy {

    private static final int MOVABLE_TO_ENEMY_COUNT = 1;

    private final int movableCount;
    private final Direction moveDirection;
    private final List<Direction> moveDirectionToEnemy;

    protected PawnMovableStrategy(int movableCount, Direction moveDirection, List<Direction> moveDirectionToEnemy) {
        this.movableCount = movableCount;
        this.moveDirection = moveDirection;
        this.moveDirectionToEnemy = moveDirectionToEnemy;
    }

    @Override
    public boolean isMovable(Position start, Position target, ChessBoard chessBoard) {
        if (existDirectionToEnemyPiecePosition(start, target, chessBoard)) {
            return isMovableToEnemyPosition(start, target);
        }
        return isMovableToPosition(start, target, chessBoard);
    }

    private boolean existDirectionToEnemyPiecePosition(Position start, Position target, ChessBoard chessBoard) {
        Piece piece = chessBoard.pieceByPosition(start);
        return !chessBoard.isPositionEmpty(target) && !piece.isSameColor(chessBoard.pieceByPosition(target));
    }

    private boolean isMovableToEnemyPosition(final Position start, final Position target) {
        return moveDirectionToEnemy.stream()
                .map(direction -> direction.route(start, target))
                .anyMatch(positions -> positions.size() == MOVABLE_TO_ENEMY_COUNT);
    }

    private boolean isMovableToPosition(final Position start, final Position target, final ChessBoard chessBoard) {
        List<Position> route = moveDirection.route(start, target);
        return existMovablePosition(route) && isClearRoute(route, chessBoard);
    }

    private boolean existMovablePosition(List<Position> route) {
        return !route.isEmpty() && route.size() <= movableCount;
    }

    private boolean isClearRoute(List<Position> route, ChessBoard chessBoard) {
        return route.stream()
                .allMatch(chessBoard::isPositionEmpty);
    }
}
