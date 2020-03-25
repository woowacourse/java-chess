package chess.board.piece;

import chess.board.Vector;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Vector vector) {
        return vector.sumOfAbsolute() == 3 && vector.subtractOfAbsolute() == 1;
    }

    @Override
    public List<Direction> findPath(final Vector vector) {
        return Collections.emptyList();
    }
}
