package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Square;

public abstract class Piece {
    protected final Camp camp;

    public Piece(final Camp camp) {
        this.camp = camp;
    }

    abstract public boolean isMovable(final Square source, final Square target);
}
