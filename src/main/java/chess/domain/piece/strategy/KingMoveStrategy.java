package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

public class KingMoveStrategy implements MoveStrategy {

    @Override
    public void validatePath(final Path path) {
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("왕은 한칸만 이동할 수 있습니다.");
        }
    }
}
