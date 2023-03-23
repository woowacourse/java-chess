package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Bishop extends Normal {

    private static final double score = 3;

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    protected Set<Position> computePath(final Position source, final Position target) {
        if (!canBishopMove(source, target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }

        return source.computeDiagonalPath(target);
    }

    @Override
    public Kind getKind() {
        return Kind.BISHOP;
    }

    private boolean canBishopMove(Position source, Position target) {
        var inclination = source.computeInclination(target);

        return Math.abs(inclination) == INCLINATION;
    }

    @Override
    public double getScore(Color color) {
        if (color == this.color) {
            return score;
        }

        return 0;
    }
}
