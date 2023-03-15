package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        validateSamePosition(source, target);

        if (source.isFileEquals(target)) {
            return getFilePath(source, target);
        }
        if (source.isRankEquals(target)) {
            return getRankPath(source, target);
        }
        throw new IllegalArgumentException();
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

    @Override
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        return false;
    }
}
