package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class King extends Piece {

    public King(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        validateSamePosition(source, target);

        if (source.isNear(target)) {
            Set<Position> path = new HashSet<>();
            path.add(target);

            return path;
        }

        throw new IllegalArgumentException();
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        return false;
    }
}
