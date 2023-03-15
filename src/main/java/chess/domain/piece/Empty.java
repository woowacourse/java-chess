package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Map;
import java.util.Set;

public final class Empty extends Piece {

    public Empty() {
        super(Color.NONE);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isEmptyPosition, final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
