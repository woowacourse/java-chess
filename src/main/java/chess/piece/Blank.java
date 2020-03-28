package chess.piece;

import chess.coordinate.Direction;
import chess.coordinate.Vector;

import java.util.List;

public class Blank extends Piece {

    private static final int BLACK_SCORE = 0;

    public Blank() {
        super(Team.NOTHING, BLACK_SCORE);
    }

    public static Piece getInstance() {
        return Pieces.BLANK.getPiece();
    }

    @Override
    public List<Direction> findPath(final Vector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean canReach(Vector vector, Piece targetPiece) {
        return false;
    }

    @Override
    public boolean isBlank() {
        return true;
    }
}
