package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

public class QueenMoveStrategy implements MoveStrategy {

    @Override
    public void validatePath(final Path path) {
        if (!path.isStraight() && !path.isDiagonal()) {
            throw new IllegalArgumentException("퀸은 대각선 혹은 직선으로만 이동가능합니다.");
        }
    }
}
