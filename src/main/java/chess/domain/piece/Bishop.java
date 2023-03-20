package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Bishop extends Normal {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (canBishopMove(source, target)) {
            return source.computeDiagonalPath(target);
        }
        throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
    }

    @Override
    public Kind getKind() {
        return Kind.BISHOP;
    }

    private boolean canBishopMove(Position source, Position target) {
        var inclination = source.computeInclination(target);

        return Math.abs(inclination) == INCLINATION;
    }
}
