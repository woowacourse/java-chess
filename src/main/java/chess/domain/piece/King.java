package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Set;

public final class King extends Normal {

    public King(final Color color) {
        super(color);
    }

    @Override
    protected Set<Position> computePath(final Position source, final Position target) {
        if (!canKingMove(source, target)) {
            throw new IllegalArgumentException(CAN_NOT_MOVE_EXCEPTION_MESSAGE);
        }

        Set<Position> path = new HashSet<>();
        path.add(target);

        return path;
    }

    @Override
    public Kind getKind() {
        return Kind.KING;
    }

    private boolean canKingMove(final Position source, final Position target) {
        var fileSub = Math.abs(source.fileSub(target));
        var rankSub = Math.abs(source.rankSub(target));
        return fileSub <= ONE_SQUARES && rankSub <= ONE_SQUARES;
    }
}
