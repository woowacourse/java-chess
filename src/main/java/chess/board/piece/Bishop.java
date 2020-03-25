package chess.board.piece;

import chess.board.Vector;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.isDiagonal();
    }

    public List<Direction> findPath(final Vector vector) {
        List<Direction> path = new ArrayList<>();
        for (int i = 0; i < vector.getMaxValue(); i++) {
            path.add(vector.getUnitVector());
        }
        return path;
    }

    @Override
    public boolean isSameTeam(final Team team) {
        return false;
    }
}
