package chess.domain.piece.strategy.constraint;

import chess.domain.piece.position.Path;

public class DecreaseRankMoveConstraint implements MoveConstraint {

    @Override
    public boolean satisfy(final Path path) {
        return path.rankInterval() < 0;
    }
}
