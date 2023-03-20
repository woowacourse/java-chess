package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Queen extends Normal {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (canQueenMove(source, target)) {
            return source.computeCrossOrDiagonalPath(target);
        }

        throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
    }

    @Override
    public Kind getKind() {
        return Kind.QUEEN;
    }

    private boolean canQueenMove(final Position source, final Position target) {
        var fileSub = source.fileSub(target);
        var rankSub = source.rankSub(target);
        var inclination = source.computeInclination(target);

        return fileSub == SAME_SQUARE || rankSub == SAME_SQUARE || Math.abs(inclination) == INCLINATION;
    }
}
