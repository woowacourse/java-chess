package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public interface PieceMovement {

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    default List<PiecePosition> waypoints(final Color currentPieceColor,
                                          final Path path,
                                          final Piece nullableEnemy) throws IllegalArgumentException {
        validateMove(currentPieceColor, path, nullableEnemy);
        return path.waypoints();
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    default void validateMove(final Color currentPieceColor,
                              final Path path,
                              final Piece nullableEnemy) throws IllegalArgumentException {
        validateAllyKill(currentPieceColor, nullableEnemy);
    }

    /**
     * @throws IllegalArgumentException 죽일 수 없는 경우
     */
    default void validateAllyKill(final Color color, final Piece enemy) throws IllegalArgumentException {
        if (enemy == null) {
            return;
        }
        if (enemy.color() == color) {
            throw new IllegalArgumentException("아군이 있는 위치로는 이동할 수 없습니다.");
        }
    }
}
