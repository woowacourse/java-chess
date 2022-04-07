package chess.domain.piece.pawn;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;
import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum PawnMoveRule {

    WHITE_PAWN(WHITE, UP, Arrays.asList(UP_RIGHT, UP_LEFT)),
    BLACK_PAWN(BLACK, DOWN, Arrays.asList(DOWN_RIGHT, DOWN_LEFT)),
    ;

    private static final int MOVABLE_TO_ENEMY_COUNT = 1;
    private static final int FIRST_MOVABLE_COUNT = 2;
    private static final int NORMAL_MOVABLE_COUNT = 1;

    private final Color color;
    private final Direction moveDirection;
    private final List<Direction> moveDirectionToEnemy;

    PawnMoveRule(Color color, Direction moveDirection, List<Direction> moveDirectionToEnemy) {
        this.color = color;
        this.moveDirection = moveDirection;
        this.moveDirectionToEnemy = moveDirectionToEnemy;
    }

    public static PawnMoveRule from(Color color) {
        Objects.requireNonNull(color, "color는 null이 들어올 수 없습니다.");
        return Arrays.stream(values())
                .filter(pawnMoveRule -> pawnMoveRule.color == color)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당 색에 존재하는 Pawn이 없습니다."));
    }

    public boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        if (chessBoard.isPositionEmpty(target)) {
            return isMovableToPosition(source, target, chessBoard);
        }
        return isMovableToEnemyPosition(source, target);
    }

    private boolean isMovableToPosition(Position source, Position target, ChessBoard chessBoard) {
        List<Position> route = moveDirection.route(source, target);
        return existMovablePosition(source, route) && isClearRoute(route, chessBoard);
    }

    private boolean existMovablePosition(Position source, List<Position> route) {
        if (route.isEmpty()) {
            return false;
        }
        if (source.isInitialPawnPosition(color)) {
            return route.size() <= FIRST_MOVABLE_COUNT;
        }
        return route.size() <= NORMAL_MOVABLE_COUNT;
    }

    private boolean isClearRoute(List<Position> route, ChessBoard chessBoard) {
        return route.stream()
                .allMatch(chessBoard::isPositionEmpty);
    }

    private boolean isMovableToEnemyPosition(Position source, Position target) {
        return moveDirectionToEnemy.stream()
                .map(direction -> direction.route(source, target))
                .anyMatch(positions -> positions.size() == MOVABLE_TO_ENEMY_COUNT);
    }
}
