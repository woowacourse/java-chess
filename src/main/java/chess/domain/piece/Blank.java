package chess.domain.piece;

import chess.domain.coordinate.Direction;
import chess.domain.coordinate.Vector;

import java.util.List;

public class Blank extends Piece {

    private static final int BLACK_SCORE = 0;

    public Blank() {
        super(Team.NOTHING, BLACK_SCORE);
    }

    @Override
    public List<Direction> findPath(final Vector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canMove(final Vector vector, final Piece targetPiece) {
        return false;
    }

    @Override
    public boolean isBlank() {
        return true;
    }
}
