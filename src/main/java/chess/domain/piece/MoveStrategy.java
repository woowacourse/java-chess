package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public interface MoveStrategy {

    boolean movable(final Path path);

    default List<PiecePosition> waypoints(final Path path) {
        if (!movable(path)) {
            throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다.");
        }
        return path.waypoints();
    }
}
