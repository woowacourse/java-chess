package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public void validatePath(final Path path) {
        if (!path.isStraight()) {
            throw new IllegalArgumentException("룩은 직선으로만 이동가능합니다.");
        }
    }
}
