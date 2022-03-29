package chess.domain.piece.pawn;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.PieceRule;
import java.util.List;

public abstract class Pawn implements PieceRule {

    private static final String PAWN_NAME = "P";
    private static final double PAWN_SCORE = 1;
    private static final int MOVABLE_TO_ENEMY_COUNT = 1;

    private final int movableCount;
    private final Direction moveDirection;
    private final List<Direction> moveDirectionToEnemy;

    protected Pawn(int movableCount, Direction moveDirection, List<Direction> moveDirectionToEnemy) {
        this.movableCount = movableCount;
        this.moveDirection = moveDirection;
        this.moveDirectionToEnemy = moveDirectionToEnemy;
    }

    protected final boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        if (chessBoard.isPositionEmpty(target)) {
            return isMovableToPosition(source, target, chessBoard);
        }
        return isMovableToEnemyPosition(source, target);
    }

    private boolean isMovableToEnemyPosition(Position source, Position target) {
        return moveDirectionToEnemy.stream()
                .map(direction -> direction.route(source, target))
                .anyMatch(positions -> positions.size() == MOVABLE_TO_ENEMY_COUNT);
    }

    private boolean isMovableToPosition(Position source, Position target, ChessBoard chessBoard) {
        List<Position> route = moveDirection.route(source, target);
        return existMovablePosition(route) && isClearRoute(route, chessBoard);
    }

    private boolean existMovablePosition(List<Position> route) {
        return !route.isEmpty() && route.size() <= movableCount;
    }

    private boolean isClearRoute(List<Position> route, ChessBoard chessBoard) {
        return route.stream()
                .allMatch(chessBoard::isPositionEmpty);
    }

    @Override
    public final String convertedName(Color color) {
        return color.convertToCase(PAWN_NAME);
    }

    @Override
    public final double score() {
        return PAWN_SCORE;
    }

    @Override
    public final boolean isPawn() {
        return true;
    }

    @Override
    public final boolean isKing() {
        return false;
    }

    protected final boolean isFirstMovePawn() {
        return movableCount == 2;
    }
}
