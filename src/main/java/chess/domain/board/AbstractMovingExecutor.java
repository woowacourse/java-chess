package chess.domain.board;

import chess.domain.piece.Placeable;

public abstract class AbstractMovingExecutor implements MovingExecutor {
    protected final Placeable placeable;

    public AbstractMovingExecutor(Placeable placeable) {
        this.placeable = placeable;
    }
}
