package chess.domain.piece;

import chess.domain.board.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public List<Position> computePath(final Position source, final Position target) {
        List<Position> path = new ArrayList<>();
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
            path = List.of(target);
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
            path = List.of(target);
        }
        return path;
    }

    @Override
    public boolean canMove(final Map<Position, Boolean> isExists, final Position source, final Position target) {
        return false;
    }
}
