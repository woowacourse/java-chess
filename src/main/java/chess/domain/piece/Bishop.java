package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Set;

public final class Bishop extends Normal {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        try {
            return source.computeDiagonalPath(target);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Kind getKind() {
        return Kind.BISHOP;
    }
}
