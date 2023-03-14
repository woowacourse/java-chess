package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;
import java.util.Map;

public final class Empty extends Piece {

    public Empty() {
        super(Color.NONE);
    }

    @Override
    public List<Position> computePath(final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
