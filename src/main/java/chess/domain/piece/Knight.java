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
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    @Override
    public Kind getKind() {
        return Kind.KNIGHT;
    }
}
