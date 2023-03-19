package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;
import java.util.List;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public void validatePath(final Path path) {
        if (Math.abs(path.fileInterval()) == 1 && Math.abs(path.rankInterval()) == 2) {
            return;
        }
        if (Math.abs(path.fileInterval()) == 2 && Math.abs(path.rankInterval()) == 1) {
            return;
        }
        throw new IllegalArgumentException("나이트는 그렇게 이동할 수 없습니다.");
    }

    @Override
    public List<PiecePosition> waypoints(final Path path) throws IllegalArgumentException {
        validatePath(path);
        return Collections.emptyList();
    }
}
