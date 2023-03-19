package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

import java.util.Collections;
import java.util.List;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable(final Path path) {
        if (Math.abs(path.fileInterval()) == 1 && Math.abs(path.rankInterval()) == 2) {
            return true;
        }
        return Math.abs(path.fileInterval()) == 2 && Math.abs(path.rankInterval()) == 1;
    }

    @Override
    public List<PiecePosition> waypoints(final Path path) {
        if (!movable(path)) {
            throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다.");
        }
        return Collections.emptyList();
    }
}
