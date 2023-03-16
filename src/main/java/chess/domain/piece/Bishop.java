package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Set;

public final class Bishop extends Piece {

    private static final double INCLINATION_ONE = 1.0d;
    private static final double INCLINATION_NEGATIVE_ONE = -1.0d;

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        validateSamePosition(source, target);

        final var inclination = source.computeInclination(target);
        if (inclination == INCLINATION_ONE) {
            return computePathInclainationOne(source, target);
        }

        if (inclination == INCLINATION_NEGATIVE_ONE) {
            return computePathInclinationNegativeOne(source, target);
        }

        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    private Set<Position> computePathInclinationNegativeOne(final Position source, final Position target) {
        Set<Position> positions = new HashSet<>();
        var max = Position.maxRank(source, target);
        var min = Position.minRank(source, target);

        while (max.isRankOver(min)) {
            max = max.getRightDownDiagonal();
            positions.add(max);
        }
        positions.add(target);
        positions.remove(source);
        return positions;
    }

    private Set<Position> computePathInclainationOne(final Position source, final Position target) {
        Set<Position> positions = new HashSet<>();
        var max = Position.maxRank(source, target);
        var min = Position.minRank(source, target);

        while (max.isRankOver(min)) {
            positions.add(max);
            max = max.getLeftDownDiagonal();
        }
        positions.add(target);
        positions.remove(source);
        return positions;
    }

    @Override
    public Kind getKind() {
        return Kind.BISHOP;
    }
}
