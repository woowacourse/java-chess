package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.Path;

public interface PawnMoveConstraint {

    void validateConstraint(final Path path);
}
