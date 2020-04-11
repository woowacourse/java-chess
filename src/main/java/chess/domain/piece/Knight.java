package chess.domain.piece;

import chess.domain.coordinate.Direction;
import chess.domain.coordinate.Vector;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(final Team team) {
        super(team, KNIGHT_SCORE);
    }

    @Override
    public boolean canMove(final Vector vector, final Piece targetPiece) {
        if (targetPiece.isSameTeam(this.team)) {
            return false;
        }
        return vector.sumOfAbsolute() == 3 && vector.subtractOfAbsolute() == 1;
    }

    @Override
    public List<Direction> findPath(final Vector vector) {
        return Collections.emptyList();
    }
}
