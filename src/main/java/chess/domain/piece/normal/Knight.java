package chess.domain.piece.normal;

import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.Position;

import java.util.Set;

public final class Knight extends Normal {

    private static final int KNIGHT_MOVE_DISTANCE = 5;
    private static final int KNIGHT_MAX_ONE_DIRECTION_DISTANCE = 3;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (canKnightMove(source, target)) {
            return Set.of(target);
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    private boolean canKnightMove(final Position source, final Position target) {
        return source.distanceEquals(target, KNIGHT_MOVE_DISTANCE)
                && source.fileSubLessThan(target, KNIGHT_MAX_ONE_DIRECTION_DISTANCE)
                && source.rankSubLessThan(target, KNIGHT_MAX_ONE_DIRECTION_DISTANCE);
    }

    @Override
    public Kind getKind() {
        return Kind.KNIGHT;
    }
}
