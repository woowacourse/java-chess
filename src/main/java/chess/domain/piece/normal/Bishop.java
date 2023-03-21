package chess.domain.piece.normal;

import chess.domain.board.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;

import java.util.Set;

public final class Bishop extends Normal {

    private static final double INCLINATION_ONE = 1.0d;
    private static final double INCLINATION_NEGATIVE_ONE = -1.0d;

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        final var inclination = source.computeInclination(target);
        if (inclination == INCLINATION_ONE) {
            return source.generateInclinationOnePath(target);
        }

        if (inclination == INCLINATION_NEGATIVE_ONE) {
            return source.generateInclinationNegativeOnePath(target);
        }

        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    @Override
    public Kind getKind() {
        return Kind.BISHOP;
    }
}
