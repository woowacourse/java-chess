package chess.domain.piece;

import chess.domain.board.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public List<Position> computePath(final Position source, final Position target) {
        if (source.isFileEquals(target)) {
            return getFilePath(source, target);
        }
        if (source.isRankEquals(target)) {
            return getRankPath(source, target);
        }
        throw new IllegalArgumentException();
    }

    private List<Position> getRankPath(final Position source, final Position target) {
        List<Position> path = new ArrayList<>();
        var max = Position.maxFile(source, target);
        var min = Position.minFile(source, target);

        while (max.isFileOver(min)) {
            path.add(max);
            max = max.getLeftStraight();
        }
        path.remove(source);
        return path;
    }

    private List<Position> getFilePath(final Position source, final Position target) {
        List<Position> path = new ArrayList<>();
        var max = Position.maxRank(source, target);
        var min = Position.minRank(source, target);

        while (max.isRankOver(min)) {
            path.add(max);
            max = max.getDownStraight();
        }
        path.remove(source);
        return path;
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        return false;
    }
}
