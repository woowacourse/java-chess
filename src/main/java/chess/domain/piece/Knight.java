package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Knight extends Normal {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (source.canKnightJump(target)) {
            return Set.of(target);
        }
        throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
    }

    @Override
    public Kind getKind() {
        return Kind.KNIGHT;
    }
}
