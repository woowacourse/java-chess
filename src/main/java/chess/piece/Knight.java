package chess.piece;

import chess.coordinate.Direction;
import chess.coordinate.Vector;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(final Team team) {
        super(team, KNIGHT_SCORE);
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        return vector.sumOfAbsolute() == 3 && vector.subtractOfAbsolute() == 1;
    }

    @Override
    public List<Direction> findPath(final Vector vector) {
        return Collections.emptyList();
    }
}
