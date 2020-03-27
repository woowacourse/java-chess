package chess.domain.board;

import chess.domain.piece.Piece;

public abstract class AbstractMovingExecutor implements MovingExecutor {
    protected final Piece piece;

    public AbstractMovingExecutor(Piece piece) {
        this.piece = piece;
    }
}
