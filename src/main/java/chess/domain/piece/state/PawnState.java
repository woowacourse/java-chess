package chess.domain.piece.state;

import chess.domain.piece.move.PawnColorMoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public abstract class PawnState {

    protected final PawnColorMoveStrategy colorMoveStrategy;

    protected PawnState(final PawnColorMoveStrategy colorMoveStrategy) {
        this.colorMoveStrategy = colorMoveStrategy;
    }

    public abstract PawnState next(final PiecePosition piecePosition);

    public abstract void validateMovable(final Path path);
}
