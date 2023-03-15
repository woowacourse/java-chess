package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        Set<Position> path = new HashSet<>();
        if (color.isBlack()) {
            path.add(source.getDownStraight());
            path.add(source.getLeftDownDiagonal());
            path.add(source.getRightDownDiagonal());
            if (source.isBlackPawnInitRank()) {
                path.add(source.getDownStraight().getDownStraight());
            }
            if (!path.contains(target)) {
                throw new IllegalArgumentException();
            }
            path = Set.of(target);
        }
        if (color.isWhite()) {
            path.add(source.getUpStraight());
            path.add(source.getLeftUpDiagonal());
            path.add(source.getRightUpDiagonal());
            if (source.isWhitePawnInitRank()) {
                path.add(source.getUpStraight().getUpStraight());
            }
            if (!path.contains(target)) {
                throw new IllegalArgumentException();
            }
            path = Set.of(target);
        }
        return path;
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        return false;
    }
}
