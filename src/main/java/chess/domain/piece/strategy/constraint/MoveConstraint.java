package chess.domain.piece.strategy.constraint;

import chess.domain.piece.position.Path;

public interface MoveConstraint {

    boolean satisfy(final Path path);
}
