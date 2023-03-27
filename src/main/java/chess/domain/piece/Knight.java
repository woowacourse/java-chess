package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Knight extends Normal {

    private static final int DISTANCE = 5;
    private static final double SCORE = 2.5;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    protected Set<Position> computePath(final Position source, final Position target) {
        if (!canKnightJump(source, target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }

        return Set.of(target);
    }

    @Override
    public Kind getKind() {
        return Kind.KNIGHT;
    }

    private boolean canKnightJump(final Position source, final Position target) {
        final var fileSub = Math.abs(source.fileSub(target));
        final var rankSub = Math.abs(source.rankSub(target));

        return Math.pow(fileSub, 2) + Math.pow(rankSub, 2) == DISTANCE;
    }

    @Override
    public double getScore(Color color) {
        if (color == this.color) {
            return SCORE;
        }

        return 0;
    }
}
