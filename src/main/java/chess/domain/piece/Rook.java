package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Rook extends Normal {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        try {
            return source.computeCrossPath(target);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Kind getKind() {
        return Kind.ROOK;
    }
}
