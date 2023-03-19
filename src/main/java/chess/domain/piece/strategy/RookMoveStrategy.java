package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable(final Path path) {
        return path.isStraight();
    }
}
