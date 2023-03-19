package chess.domain.piece.normal;

import chess.domain.board.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;

import java.util.Set;

public final class Queen extends Normal {

    private static final double POSITIVE_ONE = 1.0d;
    private static final double NEGATIVE_ONE = -1.0d;

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        final var inclination = source.computeInclination(target);
        if (inclination == POSITIVE_ONE) {
            return source.generateInclinationOnePath(target);
        }
        if (inclination == NEGATIVE_ONE) {
            return source.generateInclinationNegativeOnePath(target);
        }
        if (source.isFileEquals(target)) {
            return source.generateFilePath(target);
        }
        if (source.isRankEquals(target)) {
            return source.generateRankPath(target);
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    @Override
    public Kind getKind() {
        return Kind.QUEEN;
    }
}
