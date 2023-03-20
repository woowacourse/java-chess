package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public interface PieceMovement {

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    default List<PiecePosition> waypoints(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
        validateMove(path, nullableEnemy);
        return path.waypoints();
    }

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    void validateMove(final Path path, final Piece nullableEnemy) throws IllegalArgumentException;
}
