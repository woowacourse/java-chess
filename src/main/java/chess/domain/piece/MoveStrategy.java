package chess.domain.piece;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public interface MoveStrategy {

    /**
     * @throws IllegalArgumentException 이동할 수 없는 경로가 들어온 경우
     */
    default List<PiecePosition> waypoints(final Path path) throws IllegalArgumentException{
        validatePath(path);
        return path.waypoints();
    }

    void validatePath(final Path path);
}
