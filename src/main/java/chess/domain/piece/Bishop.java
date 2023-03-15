package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        final var inclination = source.computeInclination(target);
        if (inclination == 1.0d) {
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

        if (inclination == -1.0d) {
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

        throw new IllegalArgumentException();
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        return false;
    }
}
