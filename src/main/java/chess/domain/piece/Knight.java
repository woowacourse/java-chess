package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public final class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (source.canKnightJump(target)) {
            return Set.of(target);
        }
        throw new IllegalArgumentException();
    }
}
