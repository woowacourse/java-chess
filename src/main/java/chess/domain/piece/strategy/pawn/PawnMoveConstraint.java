package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.PiecePosition;

public interface PawnMoveConstraint {

    void validateConstraint(final PiecePosition source,
                            final PiecePosition destination);
}
