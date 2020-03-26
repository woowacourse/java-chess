package chess.board.piece;

import chess.board.Vector;

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
}
