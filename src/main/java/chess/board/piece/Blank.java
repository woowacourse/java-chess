package chess.board.piece;

import chess.board.Vector;

import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super(Team.NOTHING);
    }

    @Override
    public List<Direction> findPath(final Vector vector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canMove(final Vector vector) {
        return false;
    }
}
