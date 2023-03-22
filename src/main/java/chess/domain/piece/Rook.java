package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Rook extends Normal {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (!canRookMove(source, target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }

        return source.computeCrossPath(target);
    }

    @Override
    public Kind getKind() {
        return Kind.ROOK;
    }

    private boolean canRookMove(final Position source, final Position target) {
        var fileSub = source.fileSub(target);
        var rankSub = source.rankSub(target);

        return fileSub == SAME_SQUARE || rankSub == SAME_SQUARE;
    }
}
