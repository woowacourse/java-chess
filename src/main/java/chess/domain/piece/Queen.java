package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Queen extends Piece {

    private static final double POSITIVE_ONE = 1.0d;
    private static final double NEGATIVE_ONE = -1.0d;

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        validateSamePosition(source, target);

        final var inclination = source.computeInclination(target);
        if (inclination == POSITIVE_ONE) {
            return getPositiveOneInclinationPath(source, target);
        }

        if (inclination == NEGATIVE_ONE) {
            return getNegativeOneInclinationPath(source, target);
        }

        if (source.isFileEquals(target)) {
            return getFilePath(source, target);
        }
        if (source.isRankEquals(target)) {
            return getRankPath(source, target);
        }

        throw new IllegalArgumentException();
    }

    private Set<Position> getNegativeOneInclinationPath(final Position source, final Position target) {
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

    private Set<Position> getPositiveOneInclinationPath(final Position source, final Position target) {
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
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        return false;
    }

    private Set<Position> getRankPath(final Position source, final Position target) {
        Set<Position> path = new HashSet<>();
        var max = Position.maxFile(source, target);
        var min = Position.minFile(source, target);

        while (max.isFileOver(min)) {
            path.add(max);
            max = max.getLeftStraight();
        }
        path.add(target);
        path.remove(source);
        return path;
    }

    private Set<Position> getFilePath(final Position source, final Position target) {
        Set<Position> path = new HashSet<>();
        var max = Position.maxRank(source, target);
        var min = Position.minRank(source, target);

        while (max.isRankOver(min)) {
            path.add(max);
            max = max.getDownStraight();
        }
        path.add(target);
        path.remove(source);
        return path;
    }
}
